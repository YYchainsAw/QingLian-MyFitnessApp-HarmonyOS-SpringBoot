package com.yychainsaw.qinglianapp.network

import com.yychainsaw.qinglianapp.utils.TokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://42956d6f.r12.vip.cpolar.cn/"

    // 这是一个备用变量，主要依赖 TokenManager
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

                // ============================================================
                // 关键修改：优先调用 TokenManager.getToken()
                // ============================================================
                val token = TokenManager.getToken() ?: authToken

                if (!token.isNullOrEmpty()) {
                    requestBuilder.header("Authorization", "Bearer $token")
                }

                chain.proceed(requestBuilder.build())
            }
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)

                if (response.code == 401) {
                    authToken = null
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
