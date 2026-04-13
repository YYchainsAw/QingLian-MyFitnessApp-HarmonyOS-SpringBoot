package com.yychainsaw.qinglianapp.data.model.vo

import com.google.gson.annotations.SerializedName

// 对应接口文档中的 TokenVO
data class TokenVO(
    val token: String,

    @SerializedName("userId", alternate = ["user_id", "id"])
    val userId: String,
    val username: String,
    val nickname: String?,
    @SerializedName("avatar", alternate = ["avatar_url", "avatarUrl", "headImg"])
    val avatar: String?
)
