package com.yychainsaw.qinglianapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect // 新增导入
import androidx.compose.ui.platform.LocalContext // 新增导入
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.network.WebSocketManager
import com.yychainsaw.qinglianapp.service.WebSocketService
import com.yychainsaw.qinglianapp.ui.community.CommunityScreen
import com.yychainsaw.qinglianapp.ui.community.PostCreateScreen
import com.yychainsaw.qinglianapp.ui.friend.AddFriendScreen
import com.yychainsaw.qinglianapp.ui.login.LoginScreen
import com.yychainsaw.qinglianapp.ui.main.MainScreen
import com.yychainsaw.qinglianapp.ui.message.ChatScreen
import com.yychainsaw.qinglianapp.ui.profile.EditProfileScreen
import com.yychainsaw.qinglianapp.ui.profile.FriendsScreen
import com.yychainsaw.qinglianapp.ui.profile.ProfileScreen
import com.yychainsaw.qinglianapp.ui.profile.RecordsScreen
import com.yychainsaw.qinglianapp.ui.profile.SettingsScreen
import com.yychainsaw.qinglianapp.ui.theme.QingLianAppTheme
import com.yychainsaw.qinglianapp.utils.AppState
import com.yychainsaw.qinglianapp.utils.NotificationHelper
import com.yychainsaw.qinglianapp.utils.TokenManager
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ================== App 启动时恢复 Token ==================
        val savedToken = TokenManager.getToken(this)
        if (!savedToken.isNullOrEmpty()) {
            RetrofitClient.authToken = savedToken
            Log.d("MainActivity", "Token 已从本地存储恢复，RetrofitClient 已就绪")
        } else {
            Log.d("MainActivity", "本地无 Token，需要登录")
        }

        // 1. 初始化通知渠道
        NotificationHelper.createNotificationChannel(this)

        // 2. 启动 WebSocket 服务
        startService(Intent(this, WebSocketService::class.java))

        // 3. 全局监听 WebSocket 消息
        lifecycleScope.launch {
            WebSocketManager.messageFlow.collect { message ->
                if (AppState.currentChatFriendId != message.senderId || !AppState.isAppInForeground) {
                    NotificationHelper.showNotification(
                        context = this@MainActivity,
                        title = message.senderName ?: "新消息",
                        content = message.content
                    )
                }
            }
        }

        setContent {
            QingLianAppTheme {
                val navController = rememberNavController()
                val context = LocalContext.current // 获取 Compose 上下文

                // ================== 新增：全局监听 Token 过期事件 ==================
                LaunchedEffect(Unit) {
                    TokenManager.tokenExpiredEvent.collect {
                        Log.d("MainActivity", "收到 Token 过期事件，执行强制退出")

                        // 1. 清除本地 Token 和内存中的 Token
                        TokenManager.clearToken(context)
                        RetrofitClient.authToken = null

                        // 2. 停止 WebSocket 服务
                        val intent = Intent(context, WebSocketService::class.java)
                        context.stopService(intent)

                        // 3. 强制跳转回登录页，并清空返回栈
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
                // ===============================================================

                // 判断初始页面
                val startDestination = if (TokenManager.getToken(this).isNullOrEmpty()) "login" else "main"

                NavHost(navController = navController, startDestination = startDestination) {
                    composable("login") { LoginScreen(navController) }
                    composable("main") { MainScreen(navController) }
                    composable("add_friend") { AddFriendScreen(navController) }
                    composable("settings") { SettingsScreen(navController) }
                    composable("friends") { FriendsScreen(navController) }
                    composable("records") { RecordsScreen(navController) }
                    composable("profile") { ProfileScreen(navController) }
                    composable("edit_profile") { EditProfileScreen(navController) }
                    composable("post_create") { PostCreateScreen(navController) }
                    composable("community") {
                        CommunityScreen(
                            onPostCreate = { navController.navigate("post_create") }
                        )
                    }

                    composable(
                        route = "chat/{friendId}/{friendName}?avatar={avatar}",
                        arguments = listOf(
                            navArgument("friendId") { type = NavType.StringType },
                            navArgument("friendName") { type = NavType.StringType },
                            navArgument("avatar") { type = NavType.StringType; nullable = true }
                        )
                    ) { backStackEntry ->
                        val friendId = backStackEntry.arguments?.getString("friendId") ?: ""
                        val friendName = backStackEntry.arguments?.getString("friendName") ?: ""
                        val avatar = backStackEntry.arguments?.getString("avatar")

                        DisposableEffect(Unit) {
                            AppState.currentChatFriendId = friendId
                            onDispose {
                                AppState.currentChatFriendId = null
                            }
                        }

                        ChatScreen(navController, friendId, friendName, avatar)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        AppState.isAppInForeground = true
    }

    override fun onPause() {
        super.onPause()
        AppState.isAppInForeground = false
    }
}
