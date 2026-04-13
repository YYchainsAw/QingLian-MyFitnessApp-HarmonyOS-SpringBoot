package com.yychainsaw.qinglianapp.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.data.model.dto.UserRegisterDTO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("注册账号", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = QingLianYellow)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("用户名") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = nickname, onValueChange = { nickname = it }, label = { Text("昵称") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("密码") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) return@Button
                isLoading = true
                scope.launch {
                    try {
                        val dto = UserRegisterDTO(username, password, nickname.ifBlank { username })
                        val response = RetrofitClient.apiService.register(dto)
                        if (response.isSuccess()) {
                            Toast.makeText(context, "注册成功，请登录", Toast.LENGTH_SHORT).show()
                            // 修复：注册成功后返回上一页（登录页）
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, response.message ?: "注册失败", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "网络错误: ${e.message}", Toast.LENGTH_SHORT).show()
                    } finally {
                        isLoading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow),
            enabled = !isLoading
        ) {
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp)) else Text("注 册", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 修复：明确的返回按钮
        TextButton(onClick = { navController.popBackStack() }) {
            Text("已有账号？返回登录", color = Color.Gray)
        }
    }
}
