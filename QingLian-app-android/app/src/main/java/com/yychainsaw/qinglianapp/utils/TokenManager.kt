package com.yychainsaw.qinglianapp.utils

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object TokenManager {
    private const val PREF_NAME = "auth_prefs"
    private const val KEY_TOKEN = "jwt_token"

    // 1. 定义全局的 Token 过期事件流
    private val _tokenExpiredEvent = MutableSharedFlow<Unit>()
    val tokenExpiredEvent = _tokenExpiredEvent.asSharedFlow()

    // 用于发射事件的协程作用域
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    // 2. 提供给拦截器调用的方法，当检测到 401 时调用
    fun notifyTokenExpired() {
        scope.launch {
            _tokenExpiredEvent.emit(Unit)
        }
    }

    fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_TOKEN, null)
    }

    fun clearToken(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_TOKEN).apply()
    }
}
