package com.example.assistapp.services

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.*
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import com.google.firebase.database.FirebaseDatabase

class LocationTrackingService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var userKey: String? = null
    private val database = FirebaseDatabase.getInstance().reference.child("shared_locations")

    companion object {
        private const val CHANNEL_ID = "location_tracking_channel"
        private const val NOTIFICATION_ID = 1001
        const val EXTRA_USER_KEY = "user_key"

        fun startService(context: Context, key: String) {
            val intent = Intent(context, LocationTrackingService::class.java).apply {
                putExtra(EXTRA_USER_KEY, key)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun stopService(context: Context) {
            context.stopService(Intent(context, LocationTrackingService::class.java))
        }
    }

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createNotificationChannel()
        android.util.Log.d("LocationService", "🔵 Service onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        userKey = intent?.getStringExtra(EXTRA_USER_KEY)

        if (userKey.isNullOrEmpty()) {
            android.util.Log.e("LocationService", "❌ userKey가 없어서 서비스 종료")
            stopSelf()
            return START_NOT_STICKY
        }

        android.util.Log.d("LocationService", "✅ 서비스 시작 - userKey: $userKey")

        // Foreground 알림 시작
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)

        // 위치 추적 시작
        startLocationUpdates()

        return START_STICKY
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000L // 10초마다 업데이트
        ).apply {
            setMinUpdateIntervalMillis(5000L) // 최소 5초
            setWaitForAccurateLocation(false)
        }.build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    android.util.Log.d("LocationService", "📍 위치 수신: ${location.latitude}, ${location.longitude}")
                    uploadLocationToFirebase(location)
                }
            }
        }

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
            android.util.Log.d("LocationService", "✅ 위치 업데이트 시작됨")
        } catch (e: SecurityException) {
            android.util.Log.e("LocationService", "❌ 위치 권한 없음", e)
            stopSelf()
        } catch (e: Exception) {
            android.util.Log.e("LocationService", "❌ 위치 업데이트 시작 실패", e)
            stopSelf()
        }
    }

    private fun uploadLocationToFirebase(location: Location) {
        userKey?.let { key ->
            val locationData = hashMapOf(
                "lat" to location.latitude,
                "lon" to location.longitude,
                "timestamp" to System.currentTimeMillis()
            )

            database.child(key).setValue(locationData)
                .addOnSuccessListener {
                    android.util.Log.d("LocationService", "✅ Firebase 업로드 성공: ${location.latitude}, ${location.longitude}")
                }
                .addOnFailureListener { e ->
                    android.util.Log.e("LocationService", "❌ Firebase 업로드 실패", e)
                }
        } ?: run {
            android.util.Log.e("LocationService", "❌ userKey가 null이어서 업로드 불가")
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "위치 공유 서비스",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "백그라운드에서 위치를 공유합니다"
                setShowBadge(false)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("📍 위치 공유 중")
            .setContentText("다른 사용자가 회원님의 위치를 추적하고 있습니다")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .setAutoCancel(false)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.d("LocationService", "🛑 서비스 종료")

        // 위치 업데이트 중지
        if (::locationCallback.isInitialized) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }

        // Firebase에서 위치 정보 삭제
        userKey?.let { key ->
            database.child(key).removeValue()
                .addOnSuccessListener {
                    android.util.Log.d("LocationService", "✅ Firebase 위치 데이터 삭제 완료")
                }
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}