package com.example.assistapp

import android.app.Application
import com.kakao.vectormap.KakaoMapSdk

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Kakao Map SDK 초기화
        KakaoMapSdk.init(this, "18ce207a640e4a1f61001296ba153fde")
    }
}
