package com.yychainsaw.qinglianapp.data.model.vo

import com.google.gson.annotations.SerializedName

data class UserVO(
    @SerializedName("userId", alternate = ["user_id", "id"])
    val userId: String,

    val username: String,
    val nickname: String?,

    @SerializedName("avatarUrl", alternate = ["avatar", "headImg", "avatar_url"])
    val avatarUrl: String?,

    val gender: String?,

    // 修复：增加 "height_cm" (数据库原名) 和 "heightCm" 以防后端映射偏差
    @SerializedName("height", alternate = ["height_cm", "heightCm"])
    val height: Double?,

    // 修复：增加 "weight_kg" (数据库原名) 和 "weightKg"
    @SerializedName("weight", alternate = ["weight_kg", "weightKg"])
    val weight: Double?,

    // 增加 "last_login_time" 以匹配 SQL 原字段名
    @SerializedName("lastLoginTime", alternate = ["last_login_time"])
    val lastLoginTime: String?
)