package com.yychainsaw.qinglianapp.ui.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yychainsaw.qinglianapp.data.model.vo.FriendVO
import com.yychainsaw.qinglianapp.data.model.vo.WorkoutRecordVO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.community.resolveImageUrl
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*

// ==========================================
// 1. 我的好友页面
// ==========================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var friends by remember { mutableStateOf<List<FriendVO>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var showAddDialog by remember { mutableStateOf(false) }

    fun loadFriends() {
        scope.launch {
            isLoading = true
            try {
                val response = RetrofitClient.apiService.getFriends()
                if (response.isSuccess()) {
                    friends = response.data ?: emptyList()
                } else {
                    Toast.makeText(context, "加载好友失败: ${response.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show()
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadFriends()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我的好友", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.PersonAdd, contentDescription = "Add Friend")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = QingLianYellow)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                if (friends.isEmpty()) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(top = 100.dp), contentAlignment = Alignment.Center) {
                            Text("暂无好友，点击右上角添加", color = Color.Gray)
                        }
                    }
                } else {
                    items(friends) { friend ->
                        FriendItem(friend) {
                            // 跳转到聊天页面，参数进行 URL 编码
                            val encodedName = URLEncoder.encode(friend.nickname ?: friend.username ?: "好友", StandardCharsets.UTF_8.toString())
                            val encodedAvatar = if (friend.avatarUrl != null) URLEncoder.encode(friend.avatarUrl, StandardCharsets.UTF_8.toString()) else ""
                            navController.navigate("chat/${friend.userId}/$encodedName?avatar=$encodedAvatar")
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddFriendDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { keyword ->
                scope.launch {
                    try {
                        // 先搜索用户
                        val searchRes = RetrofitClient.apiService.searchUsers(keyword)
                        if (searchRes.isSuccess() && !searchRes.data.isNullOrEmpty()) {
                            // 发送好友请求给第一个匹配的用户
                            val targetUser = searchRes.data[0]
                            val reqRes = RetrofitClient.apiService.sendFriendRequest(targetUser.userId)
                            if (reqRes.isSuccess()) {
                                Toast.makeText(context, "好友请求已发送", Toast.LENGTH_SHORT).show()
                                showAddDialog = false
                            } else {
                                Toast.makeText(context, "请求失败: ${reqRes.message}", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "未找到用户", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}

@Composable
fun FriendItem(friend: FriendVO, onClick: () -> Unit) {
    // 优先显示昵称，其次用户名
    val displayName = friend.nickname?.takeIf { it.isNotBlank() } ?: friend.username ?: "未知好友"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = resolveImageUrl(friend.avatarUrl),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(QingLianBlue.copy(alpha = 0.1f))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = displayName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ChatBubbleOutline,
                contentDescription = "Chat",
                tint = QingLianYellow
            )
        }
    }
}

@Composable
fun AddFriendDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var keyword by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加好友") },
        text = {
            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                label = { Text("输入用户名") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = { if (keyword.isNotBlank()) onConfirm(keyword) },
                colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow)
            ) {
                Text("搜索并添加", color = Color.Black)
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } },
        containerColor = Color.White
    )
}


// ==========================================
// 2. 健身记录页面
// ==========================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var records by remember { mutableStateOf<List<WorkoutRecordVO>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // 统计数据
    val totalTime = if (records.isNotEmpty()) records.sumOf { it.durationSeconds } / 60 else 0
    val totalCalories = if (records.isNotEmpty()) records.sumOf { it.caloriesBurned } else 0

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = RetrofitClient.apiService.getWorkoutHistory()
                if (response.isSuccess() && response.data != null) {
                    // 将 DTO 转换为 VO
                    records = response.data.mapIndexed { index, dto ->
                        WorkoutRecordVO(
                            id = index.toLong(), // DTO无ID，生成临时ID用于列表渲染
                            durationSeconds = dto.durationSeconds,
                            caloriesBurned = dto.caloriesBurned,
                            notes = dto.notes,
                            workoutDate = dto.workoutDate,
                            planName = if (dto.planId != null) "计划训练" else null
                        )
                    }
                } else {
                    Toast.makeText(context, "加载记录失败", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("健身记录", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // 顶部统计概览
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatSummaryItem("总时长(分钟)", totalTime.toString(), QingLianBlue)
                StatSummaryItem("总消耗(千卡)", totalCalories.toString(), QingLianYellow)
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = QingLianYellow)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (records.isEmpty()) {
                        item {
                            Box(modifier = Modifier.fillMaxWidth().padding(top = 50.dp), contentAlignment = Alignment.Center) {
                                Text("暂无健身记录", color = Color.Gray)
                            }
                        }
                    } else {
                        items(records) { record ->
                            RecordItem(record)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatSummaryItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun RecordItem(record: WorkoutRecordVO) {
    val durationMin = record.durationSeconds / 60
    val durationSec = record.durationSeconds % 60

    val displayDate = try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MM月dd日 HH:mm", Locale.getDefault())
        val date = inputFormat.parse(record.workoutDate)
        if (date != null) outputFormat.format(date) else record.workoutDate
    } catch (e: Exception) {
        record.workoutDate
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(QingLianBlue.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = QingLianBlue)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                // 显示备注或默认标题，不显示 ID
                Text(
                    text = record.notes?.takeIf { it.isNotBlank() } ?: "自由训练",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(displayDate, fontSize = 12.sp, color = Color.Gray)
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${record.caloriesBurned} kcal",
                    fontWeight = FontWeight.Bold,
                    color = QingLianBlue,
                    fontSize = 16.sp
                )
                Text(
                    text = "${durationMin}分${durationSec}秒",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
