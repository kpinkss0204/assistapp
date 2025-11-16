package com.example.assistapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.assistapp.databinding.ActivityMainBinding
import com.example.assistapp.features.LocationSharing.LocationSharingWithCodeActivity
import com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenActivity
import com.example.assistapp.ui.WebViewScreenActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tts: TextToSpeech
    private lateinit var gestureDetector: GestureDetector
    private lateinit var prefs: SharedPreferences
    private var currentScreen = 0
    private var screens = mutableListOf("메인화면")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("settings", MODE_PRIVATE)
        tts = TextToSpeech(this, this)
        gestureDetector = GestureDetector(this, GestureListener())

        loadActiveFeatures()
        speakCurrent()

        // 오른쪽 상단 기능 관리 버튼
        binding.btnManageFeatures.setOnClickListener {
            startActivity(Intent(this, FeatureManagerActivity::class.java))
        }

        // 왼쪽 상단 메뉴 버튼
        binding.btnMenu.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            Screen.values().forEach { screen ->
                // ScheduleList는 메뉴에 표시하지 않음
                if (screen != Screen.ScheduleList) {
                    popup.menu.add("${screen.icon} ${screen.title}")
                }
            }

            popup.setOnMenuItemClickListener { item ->
                when (item.title.toString()) {
                    "${Screen.LocationSharing.icon} ${Screen.LocationSharing.title}" ->
                        startActivity(Intent(this, LocationSharingWithCodeActivity::class.java))

                    "${Screen.WebView.icon} ${Screen.WebView.title}" ->
                        startActivity(Intent(this, WebViewScreenActivity::class.java))

                    "${Screen.ScheduleSend.icon} ${Screen.ScheduleSend.title}" ->
                        startActivity(Intent(this, ScheduleSendScreenActivity::class.java))
                }
                true
            }
            popup.show()
        }
    }

    override fun onResume() {
        super.onResume()
        loadActiveFeatures()
        speakCurrent()
    }

    private fun loadActiveFeatures() {
        val activeSet = prefs.getStringSet("activeFeatures", setOf("1", "2", "3"))!!
        screens = mutableListOf("메인화면")
        for (i in activeSet.sorted()) {
            screens.add("기능 $i")
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) tts.language = Locale.KOREAN
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            if (e1 == null || e2 == null) return false
            val diffX = e2.x - e1.x
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) movePrev() else moveNext()
                return true
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            onDoubleTapAction()
            return true
        }
    }

    private fun moveNext() {
        currentScreen = (currentScreen + 1) % screens.size
        speakCurrent()
    }

    private fun movePrev() {
        currentScreen = if (currentScreen - 1 < 0) screens.size - 1 else currentScreen - 1
        speakCurrent()
    }

    private fun speakCurrent() {
        val msg = "${screens[currentScreen]}입니다. 더블탭하면 실행합니다."
        binding.textView.text = msg
        tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun onDoubleTapAction() {
        when (screens[currentScreen]) {
            "기능 1" -> startActivity(Intent(this, Feature1Activity::class.java))
            "기능 2" -> startActivity(Intent(this, Feature2Activity::class.java))
            "기능 3" -> startActivity(Intent(this, Feature3Activity::class.java))
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
