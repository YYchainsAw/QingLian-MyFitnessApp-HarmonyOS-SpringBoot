package com.yychainsaw.qinglianapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.data.model.dto.UserLoginDTO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.*
import com.yychainsaw.qinglianapp.utils.TokenManager
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("YYchainsAw") }
    var password by remember { mutableStateOf("123456") }
    var isLoading by remember { mutableStateOf(false) } // 添加加载状态
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo 区域
        Box(modifier = Modifier.size(80.dp).background(QingLianGreen, shape = RoundedCornerShape(50)))

        Spacer(modifier = Modifier.height(32.dp))

        Text("QingLian", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextBlack)
        Text("开启你的健身之旅", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(48.dp))

        // 输入框
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("用户名") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = QingLianYellow,
                focusedLabelColor = QingLianYellow
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = QingLianYellow,
                focusedLabelColor = QingLianYellow
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 登录按钮
        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "请输入用户名和密码", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                isLoading = true
                scope.launch {
                    try {
                        // 1. 构造请求对象
                        val dto = UserLoginDTO(username, password)
                        // 2. 发起请求
                        val response = RetrofitClient.apiService.login(dto)

                        if (response.isSuccess() && response.data != null) {
                            val token = response.data.token
                            // 3. 保存 Token (关键步骤！)
                            TokenManager.saveToken(context, token)
                            RetrofitClient.authToken = token

                            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()

                            // 4. 跳转到主页，并清空返回栈防止按返回键回到登录页
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, response.message ?: "登录失败", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(context, "网络错误: ${e.message}", Toast.LENGTH_SHORT).show()
                    } finally {
                        isLoading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow),
            enabled = !isLoading,
            shape = RoundedCornerShape(8.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = TextBlack)
            } else {
                Text("登 录", fontSize = 18.sp, color = TextBlack, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 补充注册入口
        TextButton(onClick = { navController.navigate("register") }) {
            Text("没有账号？去注册", color = Color.Gray)
        }
    }
}
