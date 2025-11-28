package com.example.assistapp.features.ScheduleSharing

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
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
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }   // ✅ 변경됨
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

        // ✅ ✅ ✅ 날짜 선택 (캘린더)
        item {
            CalendarDatePicker(
                selectedDate = selectedDateMillis,
                onDateSelected = { selectedDateMillis = it }
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
                    // ✅ 유효성 검사
                    if (inputKey.isEmpty()) {
                        Toast.makeText(context, "상대방 암호코드를 입력하세요", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (title.isEmpty()) {
                        Toast.makeText(context, "일정 제목을 입력하세요", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (selectedDateMillis == null) {
                        Toast.makeText(context, "날짜를 선택하세요", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (time.isNotEmpty() && !isValidTimeFormat(time)) {
                        Toast.makeText(context, "시간 형식이 올바르지 않습니다 (HH:MM)", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val formattedDate = formatDate(selectedDateMillis!!)
                    val dateTimeString =
                        if (time.isNotEmpty()) "$formattedDate $time" else "$formattedDate 00:00"

                    if (isPastDateTime(dateTimeString)) {
                        Toast.makeText(context, "⚠️ 과거 날짜/시간은 등록할 수 없습니다", Toast.LENGTH_LONG).show()
                        return@Button
                    }

                    isSending = true

                    val newSchedule = mapOf(
                        "title" to title,
                        "date" to formattedDate,
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
                            title = ""
                            selectedDateMillis = null
                            time = ""
                        }
                        .addOnFailureListener { exception ->
                            isSending = false
                            Toast.makeText(
                                context,
                                "❌ 전송 실패: ${exception.message}",
                                Toast.LENGTH_LONG
                            ).show()
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
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "1. 상대방의 암호코드를 입력하세요\n" +
                                "2. 일정 제목과 날짜를 선택하세요\n" +
                                "3. 시간은 선택사항입니다\n" +
                                "4. 과거 날짜/시간은 등록할 수 없습니다",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDatePicker(
    selectedDate: Long?,
    onDateSelected: (Long) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
    )

    Column {
        OutlinedButton(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.DateRange, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (selectedDate == null)
                    "📅 날짜 선택"
                else
                    formatDate(selectedDate)
            )
        }

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(it)
                        }
                        showDialog = false
                    }) { Text("확인") }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) { Text("취소") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

// ✅ 날짜 포맷
fun formatDate(timeMillis: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date(timeMillis))
}

// ✅ 시간 형식 검증
fun isValidTimeFormat(time: String): Boolean {
    val regex = Regex("^([0-1][0-9]|2[0-3]):[0-5][0-9]$")
    return regex.matches(time)
}

// ✅ 과거 날짜 체크
fun isPastDateTime(dateTimeString: String): Boolean {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val inputDate = sdf.parse(dateTimeString) ?: return false
        inputDate.before(Date())
    } catch (e: Exception) {
        false
    }
}
