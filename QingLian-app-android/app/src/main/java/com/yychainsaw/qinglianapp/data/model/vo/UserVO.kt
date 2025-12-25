package com.yychainsaw.qinglianapp.data.model.vo

import com.google.gson.annotations.SerializedName

data class UserVO(
    @SerializedName("userId", alternate = ["user_id", "id"])
    val userId: String,

    val username: String,
    val nickname: String?,

    @SerializedName("avatarUrl", alternate = ["avatar", "headImg"])
    val avatarUrl: String?,

    val gender: String?,

    @SerializedName("heightCm", alternate = ["height"])
    val height: Double?,

    @SerializedName("weightKg", alternate = ["weight"])
    val weight: Double?,

    val lastLoginTime: String?
)