package com.yychainsaw.qinglianapp.ui.fitness

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yychainsaw.qinglianapp.data.model.dto.MovementDTO
import com.yychainsaw.qinglianapp.data.model.vo.CategoryCountVO
import com.yychainsaw.qinglianapp.data.model.vo.MovementVO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.community.resolveImageUrl
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    // 状态管理
    var searchText by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf<List<CategoryCountVO>>(emptyList()) }
    var hardcoreMovements by remember { mutableStateOf<List<MovementVO>>(emptyList()) }
    var movementList by remember { mutableStateOf<List<MovementVO>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var showAddDialog by remember { mutableStateOf(false) }

    // 搜索逻辑
    fun performSearch(keyword: String?) {
        scope.launch {
            try {
                val response = RetrofitClient.apiService.searchMovements(
                    keyword = if (keyword.isNullOrBlank()) null else keyword,
                    pageNum = 1,
                    pageSize = 50
                )
                if (response.isSuccess()) {
                    movementList = response.data?.items ?: emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "搜索失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 初始化数据加载
    LaunchedEffect(Unit) {
        isLoading = true
        try {
            val catRes = RetrofitClient.apiService.countMovementCategories()
            if (catRes.isSuccess()) categories = catRes.data ?: emptyList()

            val hardRes = RetrofitClient.apiService.getHardcoreMovements()
            if (hardRes.isSuccess()) hardcoreMovements = hardRes.data ?: emptyList()

            performSearch(null)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                // 1. 顶部搜索栏
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            placeholder = { Text("搜索动作名称、部位...", color = Color.Gray) },
                            leadingIcon = { Icon(Icons.Default.Search, null, tint = Color.Gray) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF5F7FA),
                                unfocusedContainerColor = Color(0xFFF5F7FA),
                                disabledContainerColor = Color(0xFFF5F7FA),
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = {
                                focusManager.clearFocus()
                                performSearch(searchText)
                            })
                        )
                    }
                }

                // 2. 分类统计
                if (categories.isNotEmpty()) {
                    item {
                        Column(modifier = Modifier.padding(vertical = 12.dp)) {
                            Text(
                                "动作分类",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(categories) { category ->
                                    CategoryChip(category) {
                                        searchText = category.category ?: ""
                                        performSearch(searchText)
                                    }
                                }
                            }
                        }
                    }
                }

                // 3. 硬核挑战
                if (hardcoreMovements.isNotEmpty()) {
                    item {
                        Column(modifier = Modifier.padding(vertical = 12.dp)) {
                            Text(
                                "硬核挑战",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(hardcoreMovements) { movement ->
                                    HardcoreCard(movement)
                                }
                            }
                        }
                    }
                }

                // 4. 动作列表
                item {
                    Text(
                        "全部动作",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                }

                if (movementList.isEmpty()) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                            Text("暂无数据", color = Color.Gray)
                        }
                    }
                } else {
                    items(movementList) { movement ->
                        MovementListItem(movement)
                    }
                }
            }
        }
    }

    // 添加动作弹窗
    if (showAddDialog) {
        AddMovementDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { dto ->
                scope.launch {
                    try {
                        val res = RetrofitClient.apiService.addMovement(dto)
                        if (res.isSuccess()) {
                            Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show()
                            showAddDialog = false
                            performSearch(searchText)
                        } else {
                            Toast.makeText(context, "添加失败: ${res.message}", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
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
            Text(category.category ?: "未知", fontWeight = FontWeight.Medium, color = Color(0xFF333333))
            Spacer(modifier = Modifier.width(4.dp))
            Text("${category.count}", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun HardcoreCard(movement: MovementVO) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFEEEEEE)), // 默认灰色背景
                contentAlignment = Alignment.Center
            ) {
                // 修复：如果 videoUrl 为空，显示默认图标
                if (movement.videoUrl.isNullOrBlank()) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                } else {
                    AsyncImage(
                        model = resolveImageUrl(movement.videoUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // 难度标签
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text("Lv.${movement.difficultyLevel}", color = QingLianYellow, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(movement.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1)
                Spacer(modifier = Modifier.height(4.dp))
                Text(movement.category ?: "综合", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun MovementListItem(movement: MovementVO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // 图片区域
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.Center
            ) {
                // 修复：如果 videoUrl 为空，显示默认图标
                if (movement.videoUrl.isNullOrBlank()) {
                    Icon(
                        imageVector = Icons.Default.Image, // 或者使用 FitnessCenter
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    AsyncImage(
                        model = resolveImageUrl(movement.videoUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 信息
            Column(modifier = Modifier.weight(1f)) {
                Text(movement.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF333333))
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = QingLianBlue.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            movement.category ?: "综合",
                            fontSize = 10.sp,
                            color = QingLianBlue,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "难度: ${movement.difficultyLevel}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                if (!movement.description.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(movement.description, fontSize = 12.sp, color = Color.Gray, maxLines = 1)
                }
            }

            Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Composable
fun AddMovementDialog(onDismiss: () -> Unit, onConfirm: (MovementDTO) -> Unit) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var difficultyStr by remember { mutableStateOf("1") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加新动作") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("动作名称") })
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("分类 (如: 力量训练)") })
                OutlinedTextField(
                    value = difficultyStr,
                    onValueChange = { difficultyStr = it },
                    label = { Text("难度 (1-5)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("描述") }, maxLines = 3)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        val diff = difficultyStr.toIntOrNull() ?: 1
                        onConfirm(MovementDTO(
                            name = name,
                            description = description,
                            difficulty = diff,
                            category = category,
                            imageUrl = null
                        ))
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow)
            ) {
                Text("添加", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消", color = Color.Gray)
            }
        },
        containerColor = Color.White
    )
}
