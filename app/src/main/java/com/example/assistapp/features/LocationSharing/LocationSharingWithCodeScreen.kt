package com.example.assistapp.features.LocationSharing

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
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
import com.example.assistapp.ui.components.KakaoMapViewCompose
import com.google.firebase.database.*
import java.security.MessageDigest

fun toSafeDbKey(input: String): String {
    val hashBytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
    return hashBytes.joinToString("") { "%02x".format(it) }
}

@Composable
fun LocationSharingWithCodeScreen(
    onPartnerLocationChanged: (Pair<Double, Double>?) -> Unit = {}
) {
    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance().reference.child("shared_locations")

    var partnerLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var inputKey by remember { mutableStateOf("") }
    var partnerKeyToWatch by remember { mutableStateOf<String?>(null) }

    val lifecycleOwner = LocalLifecycleOwner.current

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val hasPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (!hasPermission) {
            Toast.makeText(context, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (!hasPermission) {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    // 상대방 위치 실시간 수신
    DisposableEffect(partnerKeyToWatch) {
        val watchKey = partnerKeyToWatch ?: return@DisposableEffect onDispose {}

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lat = snapshot.child("lat").getValue(Double::class.java)
                val lon = snapshot.child("lon").getValue(Double::class.java)
                if (lat != null && lon != null) {
                    partnerLocation = lat to lon
                    onPartnerLocationChanged(lat to lon)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                partnerLocation = null
            }
        }

        database.child(watchKey).addValueEventListener(listener)
        onDispose {
            database.child(watchKey).removeEventListener(listener)
            partnerLocation = null
        }
    }

    // 화면 이탈 시 tracking_requests 삭제
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP || event == Lifecycle.Event.ON_PAUSE) {
                partnerKeyToWatch?.let { watchKey ->
                    FirebaseDatabase.getInstance()
                        .reference.child("tracking_requests")
                        .child(watchKey)
                        .removeValue()
                }

                partnerKeyToWatch = null
                partnerLocation = null
                inputKey = ""
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)

            partnerKeyToWatch?.let { watchKey ->
                FirebaseDatabase.getInstance()
                    .reference.child("tracking_requests")
                    .child(watchKey)
                    .removeValue()
            }
        }
    }

    // UI
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Text("📍 상대방 위치 추적", style = MaterialTheme.typography.titleMedium) }

        item {
            OutlinedTextField(
                value = inputKey,
                onValueChange = { inputKey = it },
                label = { Text("상대방 암호 입력") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = partnerKeyToWatch == null
            )
        }

        item {
            Button(
                onClick = {
                    if (inputKey.isNotEmpty()) {
                        val safeKey = toSafeDbKey(inputKey.trim())
                        val trackingRequestRef = FirebaseDatabase.getInstance()
                            .reference.child("tracking_requests").child(safeKey)

                        trackingRequestRef.setValue(true)
                            .addOnSuccessListener {
                                partnerKeyToWatch = safeKey
                                Toast.makeText(context, "✅ 추적 시작", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "추적 시작 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(context, "암호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = partnerKeyToWatch == null && inputKey.isNotEmpty()
            ) {
                Text("🔍 추적 시작")
            }
        }

        if (partnerKeyToWatch != null) {
            item {
                OutlinedButton(
                    onClick = {
                        val watchKey = partnerKeyToWatch
                        if (watchKey != null) {
                            val trackingRequestRef = FirebaseDatabase.getInstance()
                                .reference.child("tracking_requests").child(watchKey)

                            trackingRequestRef.removeValue()
                                .addOnSuccessListener {
                                    partnerKeyToWatch = null
                                    partnerLocation = null
                                    inputKey = ""
                                    Toast.makeText(context, "추적 중단", Toast.LENGTH_SHORT).show()
                                }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("⏹️ 추적 중단")
                }
            }

            item { Divider() }
            item { Text("👥 상대방 위치 추적 중") }

            if (partnerLocation != null) {
                item { Text("위도: ${partnerLocation!!.first}, 경도: ${partnerLocation!!.second}") }
                item {
                    KakaoMapViewCompose(
                        lat = partnerLocation!!.first,
                        lon = partnerLocation!!.second,
                        zoom = 15,
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )
                }
            } else {
                item { Text("⏳ 상대방 위치 정보를 기다리는 중...") }
            }
        }
    }
}
