package com.yychainsaw.qinglianapp.ui.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.text.toLong

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    // 状态
    var totalCalories by remember { mutableIntStateOf(0) }
    var totalDurationMinutes by remember { mutableLongStateOf(0L) }
    var totalWorkouts by remember { mutableIntStateOf(0) }
    var bmiValue by remember { mutableStateOf(0.0) }
    var bmiStatus by remember { mutableStateOf("--") }
    // 简单的周数据 (Mon-Sun)
    var weeklyData by remember { mutableStateOf(List(7) { 0f }) } 
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                // 1. 获取用户信息计算 BMI
                val userRes = RetrofitClient.apiService.getUserInfo()
                if (userRes.isSuccess() && userRes.data != null) {
                    val u = userRes.data
                    if (u.height != null && u.weight != null && u.height > 0) {
                        val h = u.height / 100.0
                        bmiValue = u.weight / (h * h)
                        bmiStatus = when {
                            bmiValue < 18.5 -> "偏瘦"
                            bmiValue < 24.0 -> "正常"
                            bmiValue < 28.0 -> "超重"
                            else -> "肥胖"
                        }
                    }
                }

                // 2. 获取运动记录计算统计数据
                val historyRes = RetrofitClient.apiService.getWorkoutHistory()
                if (historyRes.isSuccess() && historyRes.data != null) {
                    val list = historyRes.data
                    totalWorkouts = list.size
                    totalCalories = list.sumOf { it.caloriesBurned }
                    totalDurationMinutes = list.sumOf { it.durationSeconds.toLong() } / 60

                    // 简单的按日期聚合逻辑 (模拟最近7次记录作为趋势，实际应按日期分组)
                    // 这里为了演示图表，取最近7条记录的消耗
                    val recent = list.take(7).map { it.caloriesBurned.toFloat() }.reversed()
                    // 补齐7个数据
                    weeklyData = if (recent.size < 7) {
                        List(7 - recent.size) { 0f } + recent
                    } else {
                        recent
                    }
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
                title = { Text("数据看板", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 1. 核心数据概览
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        title = "总消耗",
                        value = "$totalCalories",
                        unit = "Kcal",
                        color = Color(0xFFFF7043),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "总时长",
                        value = "$totalDurationMinutes",
                        unit = "分钟",
                        color = QingLianBlue,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "总次数",
                        value = "$totalWorkouts",
                        unit = "次",
                        color = QingLianYellow,
                        modifier = Modifier.weight(1f)
                    )
                }

                // 2. BMI 卡片
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("身体质量指数 (BMI)", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = String.format("%.1f", bmiValue),
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = QingLianBlue
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                ContainerLabel(text = bmiStatus, color = QingLianBlue)
                            }
                        }
                        // 简单的进度条示意
                        CircularProgressIndicator(
                            progress = (bmiValue / 40.0).toFloat().coerceIn(0f, 1f),
                            modifier = Modifier.size(60.dp),
                            color = QingLianYellow,
                            trackColor = Color(0xFFEEEEEE),
                            strokeWidth = 6.dp
                        )
                    }
                }

                // 3. 近期消耗趋势图
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("近期消耗趋势", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // 自定义柱状图
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            if (weeklyData.all { it == 0f }) {
                                Text("暂无足够数据", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.align(Alignment.Center))
                            } else {
                                BarChart(data = weeklyData, barColor = QingLianBlue)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, unit: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = color)
            Text(unit, fontSize = 10.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(title, fontSize = 12.sp, color = Color(0xFF333333))
        }
    }
}

@Composable
fun ContainerLabel(text: String, color: Color) {
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun BarChart(data: List<Float>, barColor: Color) {
    val maxVal = data.maxOrNull() ?: 1f
    
    Canvas(modifier = Modifier.fillMaxSize()) {
        val barWidth = size.width / (data.size * 2f)
        val spacing = size.width / data.size
        
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxVal) * size.height
            val x = spacing * index + (spacing - barWidth) / 2
            val y = size.height - barHeight
            
            drawRect(
                color = barColor,
                topLeft = Offset(x, y),
                size = Size(barWidth, barHeight)
            )
            
            // 绘制数值 (可选，如果空间足够)
            /*
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    value.toInt().toString(),
                    x + barWidth / 2,
                    y - 10,
                    android.graphics.Paint().apply {
                        textAlign = android.graphics.Paint.Align.CENTER
                        textSize = 24f
                        color = android.graphics.Color.GRAY
                    }
                )
            }
            */
        }
    }
}
