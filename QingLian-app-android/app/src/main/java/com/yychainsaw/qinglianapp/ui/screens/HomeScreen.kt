package com.yychainsaw.qinglianapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.yychainsaw.qinglianapp.ui.theme.BackgroundWhite
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow

@Composable
fun HomeScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: 发帖 */ },
                containerColor = QingLianYellow
            ) {
                Text("+", fontSize = 24.sp)
            }
        }
    ) { padding ->
        // 模拟列表数据
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BackgroundWhite),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text("热门动态", fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            // 循环生成 5 个假卡片 (之后替换为 API 数据)
            items(5) { index ->
                CommunityCard(index)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun CommunityCard(index: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 头部：头像 + 用户名
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 模拟头像
                Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(QingLianBlue))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("用户 User_$index", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                    Text("2小时前", fontSize = 12.sp, color = Color.Gray)
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 内容
            Text("今天完成了 50 个波比跳，感觉人生到达了巅峰！#健身 #打卡")
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 图片占位
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            ) {
                 // 实际使用：AsyncImage(model = "url", contentDescription = null)
                 Text("图片区域", modifier = Modifier.align(Alignment.Center))
            }

            Spacer(modifier = Modifier.height(12.dp))
            
            // 底部互动栏
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder, 
                    contentDescription = "Like",
                    tint = QingLianYellow // 点赞图标用黄色
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("102")
            }
        }
    }
}