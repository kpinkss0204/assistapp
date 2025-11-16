package com.example.assistapp.features.LocationSharing

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.assistapp.services.LocationTrackingService
import com.example.assistapp.ui.components.KakaoMapViewCompose
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp

@Composable
fun LocationSharingWithCodeScreen(
    onPartnerLocationChanged: (Pair<Double, Double>?) -> Unit = {}
) {
    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance().reference.child("shared_locations")
    val firestore = FirebaseFirestore.getInstance()
    val sharedPreferences = remember {
        context.getSharedPreferences("location_sharing_prefs", Context.MODE_PRIVATE)
    }

    var partnerLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var inputKey by remember { mutableStateOf("") }
    var generatedKey by remember {
        mutableStateOf(sharedPreferences.getString("generated_key", "") ?: "")
    }
    var hasLocationPermission by remember { mutableStateOf(false) }
    var hasNotificationPermission by remember { mutableStateOf(true) }
    var partnerKeyToWatch by remember { mutableStateOf<String?>(null) }
    var isBeingTracked by remember { mutableStateOf(false) }
    var isServiceRunning by remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasNotificationPermission = isGranted
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    // 권한 체크
    LaunchedEffect(Unit) {
        hasLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            hasNotificationPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    // 최초 암호 생성 및 Firestore 저장
    LaunchedEffect(Unit) {
        if (generatedKey.isEmpty()) {
            val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()-_=+"
            val newKey = (1..12).map { chars.random() }.joinToString("")
            generatedKey = newKey
            sharedPreferences.edit().putString("generated_key", newKey).apply()

            val messageDigest = java.security.MessageDigest.getInstance("SHA-256")
            val hashBytes = messageDigest.digest(newKey.toByteArray())
            val docId = hashBytes.joinToString("") { "%02x".format(it) }.take(32)

            val data = hashMapOf(
                "originalCode" to newKey,
                "docId" to docId,
                "createdAt" to Timestamp.now()
            )
            firestore.collection("location_keys")
                .document(docId)
                .set(data)
                .addOnSuccessListener {
                    android.util.Log.d("LocationSharing", "✅ 저장 성공 - 원본: $newKey, 문서ID: $docId")
                }
                .addOnFailureListener { exception ->
                    android.util.Log.e("LocationSharing", "❌ 저장 실패", exception)
                    Toast.makeText(context, "암호코드 저장 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    // Firebase tracking_requests 감시
    DisposableEffect(generatedKey, hasLocationPermission, hasNotificationPermission) {
        if (generatedKey.isEmpty()) return@DisposableEffect onDispose {}

        val trackingRequestRef = FirebaseDatabase.getInstance()
            .reference.child("tracking_requests").child(generatedKey)

        val requestListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val wasTracked = isBeingTracked
                isBeingTracked = snapshot.exists()

                if (isBeingTracked && !wasTracked) {
                    if (!hasLocationPermission) {
                        Toast.makeText(context, "위치 권한 필요", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission) {
                        Toast.makeText(context, "알림 권한 필요", Toast.LENGTH_SHORT).show()
                        return
                    }

                    LocationTrackingService.startService(context, generatedKey)
                    isServiceRunning = true
                    Toast.makeText(context, "🔔 백그라운드 위치 공유 시작", Toast.LENGTH_SHORT).show()
                } else if (!isBeingTracked && wasTracked) {
                    LocationTrackingService.stopService(context)
                    isServiceRunning = false
                    Toast.makeText(context, "추적 중단", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                isBeingTracked = false
                LocationTrackingService.stopService(context)
                isServiceRunning = false
            }
        }

        trackingRequestRef.addValueEventListener(requestListener)
        onDispose { trackingRequestRef.removeEventListener(requestListener) }
    }

    // 상대방 위치 실시간 수신
    DisposableEffect(partnerKeyToWatch) {
        val watchKey = partnerKeyToWatch
        if (watchKey.isNullOrEmpty()) return@DisposableEffect onDispose {}

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val lat = snapshot.child("lat").getValue(Double::class.java)
                    val lon = snapshot.child("lon").getValue(Double::class.java)
                    if (lat != null && lon != null) {
                        partnerLocation = lat to lon
                        onPartnerLocationChanged(lat to lon)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                partnerLocation = null
            }
        }

        database.child(watchKey).addValueEventListener(listener)
        onDispose { database.child(watchKey).removeEventListener(listener); partnerLocation = null }
    }

    // Lifecycle 이벤트 처리
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP, Lifecycle.Event.ON_PAUSE -> {
                    partnerKeyToWatch?.let { watchKey ->
                        FirebaseDatabase.getInstance().reference.child("tracking_requests").child(watchKey).removeValue()
                    }
                    partnerKeyToWatch = null
                    partnerLocation = null
                    inputKey = ""
                }
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    // UI
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Text("📍 위치 공유 (백그라운드 추적)", style = MaterialTheme.typography.titleMedium) }

        if (!hasLocationPermission) {
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("⚠️ 위치 권한 필요", color = MaterialTheme.colorScheme.onErrorContainer)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = {
                            locationPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
                        }) { Text("위치 권한 요청") }
                    }
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission) {
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("⚠️ 알림 권한 필요 (백그라운드)", color = MaterialTheme.colorScheme.onErrorContainer)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) }) { Text("알림 권한 요청") }
                    }
                }
            }
        }

        // 내 코드 표시
        item {
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("내 고유 암호코드")
                    Spacer(Modifier.height(4.dp))
                    Text(if (generatedKey.isNotEmpty()) generatedKey else "(생성 중...)", color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(8.dp))
                    Text("상대방에게 공유하세요", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        item {
            Button(onClick = {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("암호코드", generatedKey))
                Toast.makeText(context, "클립보드 복사 완료", Toast.LENGTH_SHORT).show()
            }, modifier = Modifier.fillMaxWidth(), enabled = generatedKey.isNotEmpty()) { Text("📋 복사") }
        }

        if (isServiceRunning) {
            item {
                OutlinedButton(onClick = {
                    LocationTrackingService.stopService(context)
                    isServiceRunning = false
                    Toast.makeText(context, "백그라운드 추적 수동 중단", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)) { Text("🛑 중단") }
            }
        }

        item { Divider() }

        // 상대방 추적
        item { Text("상대방 추적") }
        item {
            OutlinedTextField(value = inputKey, onValueChange = { inputKey = it }, label = { Text("상대방 암호 입력") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        }
        item {
            Button(onClick = {
                if (inputKey.isNotEmpty()) {
                    val trackingRequestRef = FirebaseDatabase.getInstance().reference.child("tracking_requests").child(inputKey)
                    trackingRequestRef.setValue(true).addOnSuccessListener {
                        partnerKeyToWatch = inputKey
                        Toast.makeText(context, "✅ 추적 시작", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "암호 입력 필요", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("🔍 추적 시작") }
        }

        if (partnerKeyToWatch != null) {
            item {
                OutlinedButton(onClick = {
                    partnerKeyToWatch?.let { watchKey ->
                        FirebaseDatabase.getInstance().reference.child("tracking_requests").child(watchKey).removeValue().addOnSuccessListener {
                            partnerKeyToWatch = null
                            partnerLocation = null
                            inputKey = ""
                            Toast.makeText(context, "추적 중단", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)) { Text("⏹️ 중단") }
            }

            item { Divider() }
            item { Text("👥 상대방 위치 추적 중") }

            if (partnerLocation != null) {
                item { Text("위도: ${partnerLocation!!.first}, 경도: ${partnerLocation!!.second}") }
                item {
                    KakaoMapViewCompose(lat = partnerLocation!!.first, lon = partnerLocation!!.second, zoom = 15, modifier = Modifier.fillMaxWidth().height(300.dp))
                }
            } else {
                item { Text("⏳ 상대방 위치 기다리는 중...") }
            }
        }
    }
}
