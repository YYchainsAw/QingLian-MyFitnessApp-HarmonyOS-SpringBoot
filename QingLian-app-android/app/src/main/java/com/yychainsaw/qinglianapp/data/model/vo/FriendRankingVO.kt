package com.yychainsaw.qinglianapp.data.model.vo

data class FriendRankingVO(
    val userId: String,
    val username: String,
    val avatar: String?,
    val weeklyCalories: Int,
    val rank: Int
)