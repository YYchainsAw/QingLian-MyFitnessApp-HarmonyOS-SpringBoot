package com.yychainsaw.qinglianapp.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonSubScreen(navController: NavController, title: String, content: String) {
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
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(content, color = Color.Gray)
        }
    }
}

@Composable
fun FriendsScreen(navController: NavController) {
    CommonSubScreen(navController, "我的好友", "暂无好友，快去社区认识新朋友吧！")
}

@Composable
fun RecordsScreen(navController: NavController) {
    CommonSubScreen(navController, "健身记录", "你还没有开始记录，今天练点什么？")
}
