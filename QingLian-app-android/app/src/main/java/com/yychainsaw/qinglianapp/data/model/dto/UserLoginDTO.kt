package com.yychainsaw.qinglianapp.data.model.dto

import com.google.gson.annotations.SerializedName

// 对应接口文档中的 UserLoginDTO
data class UserLoginDTO(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)

