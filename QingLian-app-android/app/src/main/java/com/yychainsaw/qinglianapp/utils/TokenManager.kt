package com.yychainsaw.qinglianapp.utils

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object TokenManager {
    private const val PREF_NAME = "auth_prefs"
    private const val KEY_TOKEN = "jwt_token"

    // 持有 SharedPreferences 实例，解决 RetrofitClient 无法获取 Context 的问题
    private var preferences: SharedPreferences? = null

    // Token 过期事件流
    private val _tokenExpiredEvent = MutableSharedFlow<Unit>()
    val tokenExpiredEvent = _tokenExpiredEvent.asSharedFlow()

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    /**
     * 必须在 Application 的 onCreate 中调用此方法进行初始化
     */
    fun init(context: Context) {
        preferences = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun notifyTokenExpired() {
        scope.launch {
            _tokenExpiredEvent.emit(Unit)
        }
    }

    // ============================================================
    // 新增：无参方法 (供 RetrofitClient 等无 Context 环境使用)
    // ============================================================

    fun getToken(): String? {
        return preferences?.getString(KEY_TOKEN, null)
    }

    fun saveToken(token: String) {
        preferences?.edit()?.putString(KEY_TOKEN, token)?.apply()
    }

    fun clearToken() {
        preferences?.edit()?.remove(KEY_TOKEN)?.apply()
    }

    // ============================================================
    // 兼容旧代码：带 Context 的方法 (自动初始化)
    // ============================================================

    fun saveToken(context: Context, token: String) {
        if (preferences == null) init(context)
        saveToken(token)
    }

    fun getToken(context: Context): String? {
        if (preferences == null) init(context)
        return getToken()
    }

    fun clearToken(context: Context) {
        if (preferences == null) init(context)
        clearToken()
    }
}
