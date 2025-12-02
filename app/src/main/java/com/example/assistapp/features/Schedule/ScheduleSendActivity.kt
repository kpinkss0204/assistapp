package com.example.assistapp.features.ScheduleSharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class ScheduleSendScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleSendScreen() // 기존 Composable 호출
        }
    }
}
