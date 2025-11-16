package com.example.assistapp.features.ScheduleSharing

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.text.ifEmpty
import kotlin.text.isEmpty
import kotlin.text.isNotEmpty
import kotlin.to

@Composable
fun ScheduleSendScreen() {
    val context = LocalContext.current
    val firestore = FirebaseFirestore.getInstance()

    // 내 암호키
    val sharedPreferences = remember {
        context.getSharedPreferences("location_sharing_prefs", Context.MODE_PRIVATE)
    }
    val generatedKey = sharedPreferences.getString("generated_key", "") ?: ""

    var inputKey by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var isSending by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("📤 일정 보내기", style = MaterialTheme.typography.titleLarge)
        }

        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "내 암호코드",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        generatedKey.ifEmpty { "(생성 안됨)" },
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        item { HorizontalDivider() }

        item {
            Text("일정 정보 입력", style = MaterialTheme.typography.titleMedium)
        }

        item {
            OutlinedTextField(
                value = inputKey,
                onValueChange = { inputKey = it },
                label = { Text("상대방 암호코드") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !isSending
            )
        }

        item {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("일정 제목") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !isSending,
                placeholder = { Text("예: 저녁 약속") }
            )
        }

        item {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("날짜 (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !isSending,
                placeholder = { Text("예: 2025-11-05") },
                supportingText = {
                    Text("오늘 날짜: ${getCurrentDate()}")
                }
            )
        }

        item {
            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("시간 (HH:MM) - 선택사항") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !isSending,
                placeholder = { Text("예: 18:30") }
            )
        }

        item {
            Button(
                onClick = {
                    // 유효성 검사
                    if (inputKey.isEmpty()) {
                        Toast.makeText(context, "상대방 암호코드를 입력하세요", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (title.isEmpty()) {
                        Toast.makeText(context, "일정 제목을 입력하세요", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (date.isEmpty()) {
                        Toast.makeText(context, "날짜를 입력하세요", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // 날짜 형식 검증
                    if (!isValidDateFormat(date)) {
                        Toast.makeText(context, "날짜 형식이 올바르지 않습니다 (YYYY-MM-DD)", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // 시간 형식 검증 (입력된 경우에만)
                    if (time.isNotEmpty() && !isValidTimeFormat(time)) {
                        Toast.makeText(context, "시간 형식이 올바르지 않습니다 (HH:MM)", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // 과거 날짜 체크
                    val dateTimeString = if (time.isNotEmpty()) "$date $time" else "$date 00:00"
                    if (isPastDateTime(dateTimeString, time.isNotEmpty())) {
                        Toast.makeText(context, "⚠️ 과거 날짜/시간은 등록할 수 없습니다", Toast.LENGTH_LONG).show()
                        return@Button
                    }

                    isSending = true

                    val newSchedule = mapOf(
                        "title" to title,
                        "date" to date,
                        "time" to time,
                        "createdAt" to Timestamp.now(),
                        "senderKey" to generatedKey
                    )

                    firestore.collection("shared_schedules")
                        .document(inputKey)
                        .collection("items")
                        .add(newSchedule)
                        .addOnSuccessListener {
                            isSending = false
                            Toast.makeText(context, "✅ 일정이 공유되었습니다!", Toast.LENGTH_SHORT).show()
                            // 입력 필드 초기화
                            title = ""
                            date = ""
                            time = ""
                        }
                        .addOnFailureListener { exception ->
                            isSending = false
                            Toast.makeText(context, "❌ 전송 실패: ${exception.message}", Toast.LENGTH_LONG).show()
                        }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSending
            ) {
                if (isSending) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(if (isSending) "전송 중..." else "📤 일정 보내기")
            }
        }

        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "💡 사용 방법",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "1. 상대방의 암호코드를 입력하세요\n" +
                                "2. 일정 제목과 날짜를 입력하세요\n" +
                                "3. 시간은 선택사항입니다\n" +
                                "4. 과거 날짜/시간은 등록할 수 없습니다",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

// 현재 날짜 반환
fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date())
}

// 날짜 형식 검증 (YYYY-MM-DD)
fun isValidDateFormat(date: String): Boolean {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.isLenient = false
        sdf.parse(date)
        true
    } catch (e: Exception) {
        false
    }
}

// 시간 형식 검증 (HH:MM)
fun isValidTimeFormat(time: String): Boolean {
    return try {
        val regex = Regex("^([0-1][0-9]|2[0-3]):[0-5][0-9]$")
        regex.matches(time)
    } catch (e: Exception) {
        false
    }
}

// 과거 날짜/시간 체크
fun isPastDateTime(dateTimeString: String, hasTime: Boolean): Boolean {
    return try {
        val format = if (hasTime) "yyyy-MM-dd HH:mm" else "yyyy-MM-dd HH:mm"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        val inputDate = sdf.parse(dateTimeString) ?: return false
        val now = Date()

        inputDate.before(now)
    } catch (e: Exception) {
        false
    }
}