package com.yychainsaw.qinglianapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.utils.TokenManager

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current

    CommonSubScreen(navController, "设置") {
        Column(modifier = Modifier.padding(top = 12.dp)) {
            // 使用下方定义的 SettingsItem
            SettingsItem(title = "账号安全") { /* TODO */ }
            SettingsItem(title = "隐私设置") { /* TODO */ }
            SettingsItem(title = "通用设置") { /* TODO */ }
            SettingsItem(title = "关于 QingLian") { /* TODO */ }

            Spacer(modifier = Modifier.height(32.dp))

            // 退出登录按钮
            Button(
                onClick = {
                    // 1. 清除本地 Token
                    TokenManager.clearToken(context)
                    // 2. 清除内存 Token
                    RetrofitClient.authToken = null
                    // 3. 跳转回登录页，并清空返回栈
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4D4F)), // 红色警示色
                shape = MaterialTheme.shapes.medium
            ) {
                Text("退出登录", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

// 修复：定义 SettingsItem 组件
@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 16.sp, color = Color(0xFF333333))
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(14.dp)
        )
    }
    Divider(color = Color(0xFFF5F5F5), thickness = 1.dp)
}
