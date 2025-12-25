package com.yychainsaw.qinglianapp.data.model

import com.google.gson.annotations.SerializedName

// 根据你的后端实际返回字段修改（有些后端叫 msg, 有些叫 message; 有些叫 code, 有些叫 status）
data class ApiResponse<T>(
    val code: Int,
    val message: String?,
    val data: T?
) {
    fun isSuccess(): Boolean = code == 0
}

data class PageBean<T>(
    val total: Long = 0,
    @SerializedName(value = "items", alternate = ["rows", "content"])
    val items: List<T> = emptyList()
)