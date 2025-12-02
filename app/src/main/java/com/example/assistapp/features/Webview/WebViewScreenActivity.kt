package com.example.assistapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize // <- Modifier import
import androidx.compose.ui.Modifier                 // <- Modifier import
import com.example.assistapp.features.Webview.WebViewScreen

class WebViewScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebViewScreen(
                url = "http://www.hsb.or.kr/",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}