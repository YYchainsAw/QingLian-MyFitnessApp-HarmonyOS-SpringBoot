package com.yychainsaw.qinglianapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.community.PostCreateScreen
import com.yychainsaw.qinglianapp.ui.login.LoginScreen
import com.yychainsaw.qinglianapp.ui.login.RegisterScreen
import com.yychainsaw.qinglianapp.ui.main.MainScreen
import com.yychainsaw.qinglianapp.ui.profile.FriendsScreen
import com.yychainsaw.qinglianapp.ui.profile.RecordsScreen
import com.yychainsaw.qinglianapp.ui.profile.SettingsScreen
import com.yychainsaw.qinglianapp.ui.theme.QingLianAppTheme
import com.yychainsaw.qinglianapp.utils.TokenManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 初始化 Token
        val token = TokenManager.getToken(this)
        RetrofitClient.authToken = token


        setContent {
            QingLianAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    val startDestination = if (TokenManager.getToken(this).isNullOrEmpty()) "login" else "main"

                    NavHost(navController = navController, startDestination = startDestination) {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("register") {
                            RegisterScreen(navController)
                        }
                        composable("main") {

                            MainScreen(navController)
                        }
                        composable("post_create") {
                            PostCreateScreen(navController)
                        }
                        composable("settings") {
                            SettingsScreen(navController)
                        }
                        composable("friends") {
                            FriendsScreen(navController)
                        }
                        composable("records") {
                            RecordsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
