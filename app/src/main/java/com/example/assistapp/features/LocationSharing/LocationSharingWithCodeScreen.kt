package com.example.assistapp.features.LocationSharing

import android.Manifest
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
    val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}

@Composable
fun LocationSharingWithCodeScreen() {

    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance().reference.child("shared_locations")

    var inputKey by remember { mutableStateOf("") }
    var partnerKey by remember { mutableStateOf<String?>(null) }
    var partnerLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var isTracking by remember { mutableStateOf(false) }

    val lifecycleOwner = LocalLifecycleOwner.current

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    // ✅ 실시간 수신
    DisposableEffect(partnerKey) {

        val key = partnerKey ?: return@DisposableEffect onDispose {}

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lat = snapshot.child("lat").getValue(Double::class.java)
                val lon = snapshot.child("lon").getValue(Double::class.java)

                partnerLocation = if (lat != null && lon != null) lat to lon else null
            }

            override fun onCancelled(error: DatabaseError) {}
        }

        database.child(key).addValueEventListener(listener)

        onDispose {
            database.child(key).removeEventListener(listener)
        }
    }

    // ✅ 화면 이탈 시 watcher 자동 OFF
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP || event == Lifecycle.Event.ON_PAUSE) {
                partnerKey?.let {
                    database.child(it).child("watcher").setValue(false)
                }
                isTracking = false
                partnerKey = null
                partnerLocation = null
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            OutlinedTextField(
                value = inputKey,
                onValueChange = { inputKey = it },
                label = { Text("상대방 암호 입력") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isTracking
            )
        }

        item {
            Button(
                onClick = {
                    val safeKey = toSafeDbKey(inputKey)

                    partnerKey = safeKey
                    isTracking = true

                    // ✅ watcher ON
                    database.child(safeKey).child("watcher").setValue(true)

                    Toast.makeText(context, "추적 시작", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isTracking && inputKey.isNotEmpty()
            ) {
                Text("추적 시작")
            }
        }

        if (isTracking) {

            item {
                OutlinedButton(
                    onClick = {
                        partnerKey?.let {
                            database.child(it).child("watcher").setValue(false)
                        }

                        isTracking = false
                        partnerKey = null
                        partnerLocation = null

                        Toast.makeText(context, "추적 중단", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("추적 중단")
                }
            }

            if (partnerLocation != null) {
                item {
                    Text("위도: ${partnerLocation!!.first}")
                    Text("경도: ${partnerLocation!!.second}")
                }

                item {
                    KakaoMapViewCompose(
                        lat = partnerLocation!!.first,
                        lon = partnerLocation!!.second,
                        zoom = 15,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            } else {
                item {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}
