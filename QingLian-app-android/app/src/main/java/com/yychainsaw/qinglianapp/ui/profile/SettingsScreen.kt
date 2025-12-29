package com.yychainsaw.qinglianapp.ui.profile

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.service.WebSocketService
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.utils.TokenManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    var isNotificationEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("设置", fontWeight = FontWeight.Bold) },
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // === 账号安全 ===
            SettingsGroupTitle("账号安全")
            SettingsItemGroup {
                SettingsItem(title = "修改密码") { Toast.makeText(context, "功能开发中", Toast.LENGTH_SHORT).show() }
                Divider(color = Color(0xFFF0F0F0), thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(title = "隐私设置") { Toast.makeText(context, "功能开发中", Toast.LENGTH_SHORT).show() }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // === 通用设置 ===
            SettingsGroupTitle("通用设置")
            SettingsItemGroup {
                SettingsSwitchItem(
                    title = "消息通知",
                    checked = isNotificationEnabled,
                    onCheckedChange = { isNotificationEnabled = it }
                )
                Divider(color = Color(0xFFF0F0F0), thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(title = "清除缓存", value = "12.8MB") {
                    Toast.makeText(context, "缓存已清除", Toast.LENGTH_SHORT).show()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // === 关于 ===
            SettingsGroupTitle("关于")
            SettingsItemGroup {
                SettingsItem(title = "版本信息", value = "v1.0.0") {}
                Divider(color = Color(0xFFF0F0F0), thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(title = "用户协议") { /* TODO */ }
                Divider(color = Color(0xFFF0F0F0), thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(title = "隐私政策") { /* TODO */ }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // === 退出登录 ===
            Button(
                onClick = {
                    // 1. 清除 Token
                    TokenManager.saveToken(context, "")
                    RetrofitClient.authToken = null

                    // 2. 停止 WebSocket 服务
                    val intent = Intent(context, WebSocketService::class.java)
                    context.stopService(intent)

                    // 3. 跳转回登录页
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text("退出登录", color = Color(0xFFFF4D4F), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// === 辅助组件 ===

@Composable
fun SettingsGroupTitle(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun SettingsItemGroup(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        content()
    }
}

@Composable
fun SettingsItem(title: String, value: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 16.sp, color = Color(0xFF333333))
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(value, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
            }
            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(14.dp))
        }
    }
}

@Composable
fun SettingsSwitchItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 16.sp, color = Color(0xFF333333))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QingLianBlue)
        )
    }
}
