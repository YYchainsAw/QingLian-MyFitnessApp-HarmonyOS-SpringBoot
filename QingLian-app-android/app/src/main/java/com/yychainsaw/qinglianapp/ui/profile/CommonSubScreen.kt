package com.yychainsaw.qinglianapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonSubScreen(navController: NavController, title: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = QingLianYellow)
            )
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            content()
        }
    }
}

@Composable
fun FriendsScreen(navController: NavController) {
    CommonSubScreen(navController, "我的好友") {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 1. 搜索与添加 (searchUsers, sendFriendRequest)
            item {
                ActionCard(
                    icon = Icons.Default.PersonAdd,
                    title = "搜索用户",
                    desc = "通过关键词查找并添加新朋友",
                    onClick = { /* TODO: searchUsers */ }
                )
            }

            // 2. 好友请求 (acceptFriendRequest)
            item {
                ActionCard(
                    icon = Icons.Default.Notifications,
                    title = "好友请求",
                    desc = "查看待处理的好友申请",
                    onClick = { /* TODO: acceptFriendRequest */ }
                )
            }

            // 3. 消息中心 (sendMessage, getUnreadMessageCount, getMessageHistory)
            item {
                ActionCard(
                    icon = Icons.Default.Chat,
                    title = "消息中心",
                    desc = "查看未读消息和聊天记录",
                    onClick = { /* TODO: Message APIs */ }
                )
            }

            // 4. 排行榜与计划 (getFriendRankings, getFriendPlans)
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        SmallActionCard("好友排行", Icons.Default.Leaderboard) { /* TODO: getFriendRankings */ }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        SmallActionCard("好友计划", Icons.Default.EventNote) { /* TODO: getFriendPlans */ }
                    }
                }
            }

            // 5. 好友列表 (deleteFriend)
            item {
                Text("好友列表", fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
                Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                    Text("暂无好友", color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun RecordsScreen(navController: NavController) {
    CommonSubScreen(navController, "健身记录") {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 1. 今日概览 (getTodayCalories)
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = QingLianBlue),
                    modifier = Modifier.fillMaxWidth().height(120.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("今日消耗 (Kcal)", color = Color.White, fontSize = 14.sp)
                        Text("0", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold) // TODO: getTodayCalories
                    }
                }
            }

            // 2. 快速记录 (addWorkoutRecord, logWorkoutByMovement)
            item {
                ActionCard(
                    icon = Icons.Default.AddCircle,
                    title = "开始记录",
                    desc = "添加新的运动记录或按动作打卡",
                    onClick = { /* TODO: addWorkoutRecord */ }
                )
            }

            // 3. 计划管理 (createPlan, getActivePlans, completePlan)
            item {
                ActionCard(
                    icon = Icons.Default.Assignment,
                    title = "我的计划",
                    desc = "创建新计划或查看进行中的计划",
                    onClick = { /* TODO: Plan APIs */ }
                )
            }

            // 4. 历史与排行 (getWorkoutHistory, getBurnLeaderboard)
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        SmallActionCard("历史记录", Icons.Default.History) { /* TODO: getWorkoutHistory */ }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        SmallActionCard("燃脂排行", Icons.Default.EmojiEvents) { /* TODO: getBurnLeaderboard */ }
                    }
                }
            }
        }
    }
}

@Composable
fun ActionCard(icon: ImageVector, title: String, desc: String, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = QingLianYellow, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(desc, color = Color.Gray, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun SmallActionCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontSize = 14.sp)
        }
    }
}
