package com.example.assistapp.features.Schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.assistapp.features.ScheduleSharing.ScheduleSendScreen


class ScheduleSendScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleSendScreen() // 기존 Composable 함수 호출
        }
    }
}