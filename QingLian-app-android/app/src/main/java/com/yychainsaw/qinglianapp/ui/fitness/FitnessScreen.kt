package com.yychainsaw.qinglianapp.ui.fitness

import android.net.Uri
import android.widget.Toast
import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.yychainsaw.qinglianapp.data.model.dto.MovementDTO
import com.yychainsaw.qinglianapp.data.model.dto.WorkoutRecordDTO
import com.yychainsaw.qinglianapp.data.model.vo.*
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

// 辅助函数：解析图片 URL (与 CommunityScreen 类似)
fun resolveFitnessImageUrl(url: String?): String {
    if (url.isNullOrBlank()) return ""
    if (url.startsWith("http://") || url.startsWith("https://")) {
        return url
    }
    val cleanBase = "https://5725cab8.r12.vip.cpolar.cn".trimEnd('/')
    val cleanPath = url.trimStart('/')
    return "$cleanBase/$cleanPath"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // === 状态管理 ===
    var searchText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    var playingVideoUrl by remember { mutableStateOf<String?>(null) }

    // 数据源
    var categories by remember { mutableStateOf<List<CategoryCountVO>>(emptyList()) }
    var hardcoreMovements by remember { mutableStateOf<List<MovementVO>>(emptyList()) }
    var movementList by remember { mutableStateOf<List<MovementVO>>(emptyList()) }
    var activePlans by remember { mutableStateOf<List<PlanVO>>(emptyList()) }
    var todayCalories by remember { mutableIntStateOf(0) }
    var leaderboard by remember { mutableStateOf<List<BurnRankVO>>(emptyList()) }

    // 弹窗控制
    var showAddMovementDialog by remember { mutableStateOf(false) }
    var showBMIDialog by remember { mutableStateOf(false) }
    var showTimerDialog by remember { mutableStateOf(false) }
    var showLeaderboardDialog by remember { mutableStateOf(false) }
    var showLogWorkoutDialog by remember { mutableStateOf(false) }

    // === API 方法 ===
    fun performSearch(keyword: String?) {
        scope.launch {
            try {
                val res = RetrofitClient.apiService.searchMovements(keyword)
                if (res.isSuccess()) {
                    movementList = res.data?.items ?: emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun refreshDashboard() {
        scope.launch {
            try {
                val calRes = RetrofitClient.apiService.getTodayCalories()
                if (calRes.isSuccess()) todayCalories = calRes.data ?: 0

                val planRes = RetrofitClient.apiService.getActivePlans()
                if (planRes.isSuccess()) activePlans = planRes.data ?: emptyList()

                val rankRes = RetrofitClient.apiService.getBurnLeaderboard()
                if (rankRes.isSuccess()) leaderboard = rankRes.data ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // === 初始化 ===
    LaunchedEffect(Unit) {
        isLoading = true
        try {
            val catRes = RetrofitClient.apiService.countMovementCategories()
            if (catRes.isSuccess()) categories = catRes.data ?: emptyList()
            val hardRes = RetrofitClient.apiService.getHardcoreMovements()
            if (hardRes.isSuccess()) hardcoreMovements = hardRes.data ?: emptyList()
            performSearch(null)
            refreshDashboard()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddMovementDialog = true },
                containerColor = QingLianYellow,
                contentColor = Color.Black
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Movement")
            }
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        if (isLoading && movementList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = QingLianYellow)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                // 1. 顶部概览卡片
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        colors = CardDefaults.cardColors(containerColor = QingLianBlue),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text("今日消耗 (Kcal)", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("$todayCalories", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                ToolItem(Icons.Default.Timer, "计时器", Color.White) { showTimerDialog = true }
                                ToolItem(Icons.Default.Calculate, "BMI计算", Color.White) { showBMIDialog = true }
                                ToolItem(Icons.Default.Leaderboard, "排行榜", Color.White) { showLeaderboardDialog = true }
                                ToolItem(Icons.Default.EditCalendar, "打卡", Color.White) { showLogWorkoutDialog = true }
                            }
                        }
                    }
                }

                // 2. 进行中的计划
                if (activePlans.isNotEmpty()) {
                    item {
                        Text("进行中的计划", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(activePlans) { plan ->
                                PlanCard(plan)
                            }
                        }
                    }
                }

                // 3. 硬核动作推荐
                if (hardcoreMovements.isNotEmpty()) {
                    item {
                        Text("硬核挑战", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(hardcoreMovements) { mv ->
                                HardcoreCard(mv) { playingVideoUrl = mv.videoUrl }
                            }
                        }
                    }
                }

                // 4. 动作库搜索与列表
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("动作库", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it
                                performSearch(it.ifBlank { null })
                            },
                            placeholder = { Text("搜索动作...") },
                            leadingIcon = { Icon(Icons.Default.Search, null) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        // 分类 Chips
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(categories) { cat ->
                                CategoryChip(cat) { performSearch(cat.category) }
                            }
                        }
                    }
                }

                items(movementList) { mv ->
                    MovementListItem(mv) { playingVideoUrl = mv.videoUrl }
                }
            }
        }
    }

    // === 视频播放弹窗 ===
    if (playingVideoUrl != null) {
        VideoPlayerDialog(url = playingVideoUrl!!) {
            playingVideoUrl = null
        }
    }

    // === 各种功能弹窗 ===

    if (showAddMovementDialog) {
        AddMovementDialog(
            onDismiss = { showAddMovementDialog = false },
            onConfirm = { dto ->
                scope.launch {
                    try {
                        RetrofitClient.apiService.addMovement(dto)
                        Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show()
                        showAddMovementDialog = false
                        performSearch(null)
                    } catch (e: Exception) {
                        Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }

    if (showBMIDialog) {
        BMICalculatorDialog(onDismiss = { showBMIDialog = false })
    }

    if (showTimerDialog) {
        TimerDialog(onDismiss = { showTimerDialog = false })
    }

    if (showLeaderboardDialog) {
        LeaderboardDialog(leaderboard = leaderboard, onDismiss = { showLeaderboardDialog = false })
    }

    if (showLogWorkoutDialog) {
        QuickLogDialog(
            onDismiss = { showLogWorkoutDialog = false },
            onConfirm = { duration, calories ->
                scope.launch {
                    try {
                        // 简单的记录逻辑，实际可能需要选择动作
                        Toast.makeText(context, "打卡成功: $duration 分钟, $calories 千卡", Toast.LENGTH_SHORT).show()
                        showLogWorkoutDialog = false
                        refreshDashboard()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        )
    }
}

// === 视频播放器组件 ===
@Composable
fun VideoPlayerDialog(url: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("视频演示") },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    factory = { ctx ->
                        VideoView(ctx).apply {
                            setVideoURI(Uri.parse(url))
                            start()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("关闭") }
        },
        containerColor = Color.White
    )
}

// === UI 组件 ===

@Composable
fun ToolItem(icon: ImageVector, label: String, color: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.White.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = color)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 12.sp, color = color)
    }
}

@Composable
fun PlanCard(plan: PlanVO) {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var totalDays = 1L
    var daysPassed = 0L

    try {
        val start = sdf.parse(plan.startDate)
        val end = sdf.parse(plan.endDate)
        val now = Date()

        if (start != null && end != null) {
            val diff = end.time - start.time
            totalDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).coerceAtLeast(1)

            val passedDiff = now.time - start.time
            daysPassed = TimeUnit.DAYS.convert(passedDiff, TimeUnit.MILLISECONDS).coerceAtLeast(0)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val progress = (daysPassed.toFloat() / totalDays).coerceIn(0f, 1f)

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(plan.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 1)
            Spacer(modifier = Modifier.height(4.dp))
            Text("目标: ${totalDays}天", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = QingLianYellow,
                trackColor = Color(0xFFEEEEEE)
            )
        }
    }
}

@Composable
fun CategoryChip(category: CategoryCountVO, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE)),
        modifier = Modifier.height(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(category.category, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text("(${category.count})", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun HardcoreCard(movement: MovementVO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            // 视频占位区域
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.PlayCircle, null, tint = Color.White, modifier = Modifier.size(32.dp))
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(movement.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1)
                Spacer(modifier = Modifier.height(4.dp))
                Text("难度: ${movement.difficultyLevel}", fontSize = 12.sp, color = QingLianBlue)
            }
        }
    }
}

@Composable
fun MovementListItem(movement: MovementVO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // 视频占位区域
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.PlayArrow, null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(movement.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                movement.category?.let { Text(it, fontSize = 12.sp, color = Color.Gray) }
            }

            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

// === 弹窗组件定义 ===

@Composable
fun BMICalculatorDialog(onDismiss: () -> Unit) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("BMI 计算器") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("身高 (cm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("体重 (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                if (result != null) {
                    Text("您的 BMI: $result", color = QingLianBlue, fontWeight = FontWeight.Bold)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val h = height.toDoubleOrNull()
                    val w = weight.toDoubleOrNull()
                    if (h != null && w != null && h > 0) {
                        val bmi = w / ((h / 100) * (h / 100))
                        result = String.format("%.1f", bmi)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow)
            ) {
                Text("计算", color = Color.Black)
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("关闭") } },
        containerColor = Color.White
    )
}

@Composable
fun TimerDialog(onDismiss: () -> Unit) {
    var timeSeconds by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000L)
            timeSeconds++
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("简易计时器") },
        text = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = String.format("%02d:%02d", timeSeconds / 60, timeSeconds % 60),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = QingLianBlue
                )
            }
        },
        confirmButton = {
            Row {
                Button(
                    onClick = { isRunning = !isRunning },
                    colors = ButtonDefaults.buttonColors(containerColor = if (isRunning) Color.Red else QingLianYellow)
                ) {
                    Text(if (isRunning) "暂停" else "开始", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        isRunning = false
                        timeSeconds = 0
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("重置")
                }
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("关闭") } },
        containerColor = Color.White
    )
}

@Composable
fun LeaderboardDialog(leaderboard: List<BurnRankVO>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("燃脂排行榜 (Top 10)") },
        text = {
            Box(modifier = Modifier.heightIn(max = 400.dp)) {
                if (leaderboard.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth().padding(20.dp), contentAlignment = Alignment.Center) {
                        Text("暂无数据", color = Color.Gray)
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(leaderboard) { item ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // 排名
                                Text(
                                    text = "#${leaderboard.indexOf(item) + 1}",
                                    fontWeight = FontWeight.Bold,
                                    color = QingLianYellow,
                                    modifier = Modifier.width(30.dp)
                                )
                                // 头像
                                AsyncImage(
                                    model = resolveFitnessImageUrl(item.avatarUrl),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                // 名字和热量
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(item.nickname ?: "用户", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Text("${item.totalCalories} kcal", fontSize = 12.sp, color = Color.Gray)
                                }
                            }
                            Divider(color = Color(0xFFF0F0F0), thickness = 1.dp, modifier = Modifier.padding(top = 8.dp))
                        }
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("关闭") } },
        containerColor = Color.White
    )
}

@Composable
fun QuickLogDialog(onDismiss: () -> Unit, onConfirm: (Int, Int) -> Unit) {
    var duration by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("快速打卡") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("时长 (分钟)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("消耗 (千卡)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val d = duration.toIntOrNull() ?: 0
                    val c = calories.toIntOrNull() ?: 0
                    if (d > 0 && c > 0) {
                        onConfirm(d, c)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow)
            ) {
                Text("打卡", color = Color.Black)
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } },
        containerColor = Color.White
    )
}

@Composable
fun AddMovementDialog(onDismiss: () -> Unit, onConfirm: (MovementDTO) -> Unit) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var difficultyStr by remember { mutableStateOf("1") }
    var description by remember { mutableStateOf("") }
    var videoUrl by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加新动作") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("动作名称") })
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("分类") })
                OutlinedTextField(
                    value = difficultyStr,
                    onValueChange = { difficultyStr = it },
                    label = { Text("难度 (1-5)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(value = videoUrl, onValueChange = { videoUrl = it }, label = { Text("视频链接 (MP4)") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("描述") }, maxLines = 3)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        val diff = difficultyStr.toIntOrNull() ?: 1
                        onConfirm(MovementDTO(name, category, diff, description, videoUrl))
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow)
            ) {
                Text("添加", color = Color.Black)
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } },
        containerColor = Color.White
    )
}
