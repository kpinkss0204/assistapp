package com.example.assistapp

enum class Screen(val title: String, val icon: String) {
    LocationSharing("위치 공유", "📍"),
    WebView("웹뷰", "🌐"),
    ScheduleSend("일정 보내기", "📤"),
    ScheduleList("일정 목록", "📥")  // 메뉴에는 표시 안 됨
}
