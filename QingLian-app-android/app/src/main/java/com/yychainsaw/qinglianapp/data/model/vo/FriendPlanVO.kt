package com.yychainsaw.qinglianapp.data.model.vo

data class FriendPlanVO(
    val friendId: String,
    val friendName: String,
    val friendAvatar: String?,
    val planName: String,
    val progress: Int // 0-100
)