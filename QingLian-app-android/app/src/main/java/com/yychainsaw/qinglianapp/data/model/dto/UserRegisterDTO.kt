package com.yychainsaw.qinglianapp.data.model.dto

import com.google.gson.annotations.SerializedName

data class UserRegisterDTO(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("nickname") val nickname: String
)