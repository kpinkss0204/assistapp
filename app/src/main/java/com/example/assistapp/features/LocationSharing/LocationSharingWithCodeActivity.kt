package com.example.assistapp.features.LocationSharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class LocationSharingWithCodeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationSharingWithCodeScreen() // 기존 Composable 호출
        }
    }
}
