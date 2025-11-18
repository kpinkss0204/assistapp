package com.example.assistapp.services

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.*
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp

class LocationTrackingService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var userKey: String? = null
    private val realtimeDb = FirebaseDatabase.getInstance().reference
    private val firestore = FirebaseFirestore.getInstance()
    private var trackingRequestListener: ValueEventListener? = null

    companion object {
        private const val CHANNEL_ID = "location_tracking_channel"
        private const val NOTIFICATION_ID = 1001
        const val EXTRA_USER_KEY = "user_key"
        const val ACTION_INIT = "ACTION_INIT"
        const val ACTION_START_TRACKING = "ACTION_START_TRACKING"
        const val ACTION_STOP_TRACKING = "ACTION_STOP_TRACKING"

        // 1. 앱 설치/실행 시 암호코드 생성 및 Firestore 저장
        fun initializeAndGenerateKey(context: Context) {
            val sharedPreferences = context.getSharedPreferences("location_sharing_prefs", Context.MODE_PRIVATE)
            var generatedKey = sharedPreferences.getString("generated_key", "") ?: ""

            if (generatedKey.isEmpty()) {
                // 암호코드 생성
                val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()-_=+"
                generatedKey = (1..12).map { chars.random() }.joinToString("")
                sharedPreferences.edit().putString("generated_key", generatedKey).apply()

                // Firestore에 저장
                val messageDigest = java.security.MessageDigest.getInstance("SHA-256")
                val hashBytes = messageDigest.digest(generatedKey.toByteArray())
                val docId = hashBytes.joinToString("") { "%02x".format(it) }.take(32)

                val data = hashMapOf(
                    "originalCode" to generatedKey,
                    "docId" to docId,
                    "createdAt" to Timestamp.now()
                )

                FirebaseFirestore.getInstance()
                    .collection("location_keys")
                    .document(docId)
                    .set(data)
                    .addOnSuccessListener {
                        android.util.Log.d("LocationService", "✅ Firestore 암호코드 저장 성공 - $docId")
                    }
                    .addOnFailureListener { e ->
                        android.util.Log.e("LocationService", "❌ Firestore 암호코드 저장 실패", e)
                    }
            }

            // 서비스 시작하여 tracking_requests 감시 시작
            val intent = Intent(context, LocationTrackingService::class.java).apply {
                action = ACTION_INIT
                putExtra(EXTRA_USER_KEY, generatedKey)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        // 2. 상대방 추적 시작 (암호 입력 후)
        fun startTracking(context: Context, partnerKey: String) {
            // Realtime DB에 tracking_requests 기록
            FirebaseDatabase.getInstance()
                .reference
                .child("tracking_requests")
                .child(partnerKey)
                .setValue(true)
                .addOnSuccessListener {
                    android.util.Log.d("LocationService", "✅ 추적 요청 전송 완료: $partnerKey")
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
        val action = intent?.action
        userKey = intent?.getStringExtra(EXTRA_USER_KEY)

        if (userKey.isNullOrEmpty()) {
            android.util.Log.e("LocationService", "❌ userKey가 없어서 서비스 종료")
            stopSelf()
            return START_NOT_STICKY
        }

        android.util.Log.d("LocationService", "✅ 서비스 시작 - userKey: $userKey, action: $action")

        when (action) {
            ACTION_INIT -> {
                // 3. tracking_requests 감시 시작
                startMonitoringTrackingRequests()
                val notification = createNotification("대기 중", "추적 요청 대기 중입니다")
                startForeground(NOTIFICATION_ID, notification)
            }
            ACTION_START_TRACKING -> {
                // 위치 추적 시작
                startLocationUpdates()
                val notification = createNotification("위치 공유 중", "다른 사용자가 회원님의 위치를 추적하고 있습니다")
                startForeground(NOTIFICATION_ID, notification)
            }
        }

        return START_STICKY
    }

    // 3. tracking_requests 감시
    private fun startMonitoringTrackingRequests() {
        userKey?.let { key ->
            val trackingRequestRef = realtimeDb.child("tracking_requests").child(key)

            trackingRequestListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists() && snapshot.getValue(Boolean::class.java) == true) {
                        android.util.Log.d("LocationService", "🔔 추적 요청 감지! 위치 공유 시작")
                        // 위치 추적 시작
                        startLocationUpdates()
                        updateNotification("위치 공유 중", "다른 사용자가 회원님의 위치를 추적하고 있습니다")
                    } else {
                        android.util.Log.d("LocationService", "⏹️ 추적 요청 없음. 위치 공유 중단")
                        stopLocationUpdates()
                        updateNotification("대기 중", "추적 요청 대기 중입니다")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    android.util.Log.e("LocationService", "❌ tracking_requests 감시 실패", error.toException())
                }
            }

            trackingRequestRef.addValueEventListener(trackingRequestListener!!)
            android.util.Log.d("LocationService", "👀 tracking_requests 감시 시작: $key")
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (::locationCallback.isInitialized) {
            android.util.Log.d("LocationService", "⚠️ 이미 위치 추적 중")
            return
        }

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
                    uploadLocationToRealtimeDB(location)
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
        } catch (e: Exception) {
            android.util.Log.e("LocationService", "❌ 위치 업데이트 시작 실패", e)
        }
    }

    private fun stopLocationUpdates() {
        if (::locationCallback.isInitialized) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
            android.util.Log.d("LocationService", "🛑 위치 업데이트 중단")
        }
    }

    // 4. Realtime DB에 위치 정보 업로드
    private fun uploadLocationToRealtimeDB(location: Location) {
        userKey?.let { key ->
            val locationData = hashMapOf(
                "lat" to location.latitude,
                "lon" to location.longitude,
                "timestamp" to System.currentTimeMillis()
            )

            realtimeDb.child("shared_locations").child(key).setValue(locationData)
                .addOnSuccessListener {
                    android.util.Log.d("LocationService", "✅ Realtime DB 업로드 성공: ${location.latitude}, ${location.longitude}")
                }
                .addOnFailureListener { e ->
                    android.util.Log.e("LocationService", "❌ Realtime DB 업로드 실패", e)
                }
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

    private fun createNotification(title: String, text: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .setAutoCancel(false)
            .build()
    }

    private fun updateNotification(title: String, text: String) {
        val notification = createNotification(title, text)
        val manager = getSystemService(NotificationManager::class.java)
        manager?.notify(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.d("LocationService", "🛑 서비스 종료")

        // tracking_requests 감시 중단
        userKey?.let { key ->
            trackingRequestListener?.let {
                realtimeDb.child("tracking_requests").child(key).removeEventListener(it)
            }
        }

        // 위치 업데이트 중지
        stopLocationUpdates()

        // Realtime DB에서 위치 정보 삭제
        userKey?.let { key ->
            realtimeDb.child("shared_locations").child(key).removeValue()
                .addOnSuccessListener {
                    android.util.Log.d("LocationService", "✅ Realtime DB 위치 데이터 삭제 완료")
                }
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}