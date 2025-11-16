package com.example.assistapp.features.camera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class CameraActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // 권한이 허용되면 화면 표시
            setContent {
                CameraScreen()
            }
        } else {
            // 권한이 거부되면 종료
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 카메라 권한 확인
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // 권한이 이미 있으면 바로 화면 표시
                setContent {
                    CameraScreen()
                }
            }
            else -> {
                // 권한 요청
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}