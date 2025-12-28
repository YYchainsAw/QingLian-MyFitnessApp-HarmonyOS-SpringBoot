package com.yychainsaw.qinglianapp.network

import com.yychainsaw.qinglianapp.utils.TokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.code

object RetrofitClient {
    private const val BASE_URL = "https://7ec4c34b.r7.cpolar.top/"

    // 全局保存 Token，在 MainActivity 启动或登录成功时赋值
    var authToken: String? = null

    private val client: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()

                // 如果有 Token，则添加到 Header
                authToken?.let { token ->
                    // 根据你的接口文档，Header 是 Authorization: <token>
                    requestBuilder.header("Authorization", token)
                }

                chain.proceed(requestBuilder.build())
            }
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)

                // 如果后端返回 401 Unauthorized，说明 Token 过期或无效
                if (response.code == 401) {
                    TokenManager.notifyTokenExpired()
                }

                response
            }
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
