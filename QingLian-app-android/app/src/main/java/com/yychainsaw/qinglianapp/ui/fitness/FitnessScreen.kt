package com.yychainsaw.qinglianapp.ui.fitness

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yychainsaw.qinglianapp.data.model.vo.MovementVO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow

// 定义难度颜色
val LevelEasy = Color(0xFF4CAF50)   // 绿色
val LevelMedium = Color(0xFFFF9800) // 橙色
val LevelHard = Color(0xFFF44336)   // 红色
val BackgroundGray = Color(0xFFF5F7FA)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FitnessScreen() {
    var movements by remember { mutableStateOf<List<MovementVO>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // 数据加载逻辑
    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.searchMovements(keyword = null, pageNum = 1, pageSize = 100) // 获取更多数据以展示分组效果
            if (response.isSuccess() && response.data != null) {
                movements = response.data.items
            } else {
                errorMessage = response.message ?: "加载失败"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = "网络错误: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    // 将数据按难度分组并排序
    val groupedMovements = remember(movements) {
        movements.groupBy { it.difficultyLevel }.toSortedMap()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("健身动作库", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = QingLianYellow
                )
            )
        },
        containerColor = BackgroundGray
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = QingLianYellow
                )
            } else if (errorMessage != null) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = errorMessage!!, color = Color.Red)
                    Button(onClick = { /* 重试逻辑 */ }, colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow)) {
                        Text("重试", color = Color.Black)
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    groupedMovements.forEach { (level, list) ->
                        // 粘性标题：滑动时吸顶
                        stickyHeader {
                            DifficultyHeader(level)
                        }

                        items(list) { movement ->
                            MovementCard(movement)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DifficultyHeader(level: Int) {
    val (label, color) = when (level) {
        1 -> "入门基础 (Level 1)" to LevelEasy
        2 -> "初级进阶 (Level 2)" to LevelEasy
        3 -> "中级强化 (Level 3)" to LevelMedium
        4 -> "高级训练 (Level 4)" to LevelHard
        5 -> "极限挑战 (Level 5)" to LevelHard
        else -> "其他 (Level $level)" to Color.Gray
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundGray) // 防止透明背景导致重叠
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 4.dp, height = 16.dp)
                .background(color, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

@Composable
fun MovementCard(movement: MovementVO) {
    // 根据难度确定颜色
    val difficultyColor = when (movement.difficultyLevel) {
        in 1..2 -> LevelEasy
        3 -> LevelMedium
        else -> LevelHard
    }

    // 根据分类确定图标
    val categoryIcon = getCategoryIcon(movement.category)

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧：图标区域
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(difficultyColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = categoryIcon,
                    contentDescription = null,
                    tint = difficultyColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 中间：文本信息
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = movement.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // 分类标签
                    Surface(
                        color = Color(0xFFF0F0F0),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = movement.category ?: "综合",
                            fontSize = 10.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movement.description ?: "暂无描述",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    lineHeight = 16.sp
                )
            }

            // 右侧：如果有视频，显示播放按钮
            if (!movement.videoUrl.isNullOrEmpty()) {
                IconButton(onClick = { /* TODO: 播放视频 */ }) {
                    Icon(
                        imageVector = Icons.Default.PlayCircleFilled,
                        contentDescription = "Play",
                        tint = QingLianYellow,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

// 辅助函数：根据分类名称返回图标
fun getCategoryIcon(category: String?): ImageVector {
    return when (category) {
        "力量训练" -> Icons.Default.FitnessCenter
        "有氧运动" -> Icons.Default.DirectionsRun
        "HIIT" -> Icons.Default.FlashOn
        "核心训练" -> Icons.Default.SelfImprovement
        "拉伸" -> Icons.Default.SelfImprovement
        else -> Icons.Default.Timer
    }
}
