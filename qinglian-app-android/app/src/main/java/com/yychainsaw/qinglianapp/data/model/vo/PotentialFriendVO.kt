package com.yychainsaw.qinglianapp.data.model.vo

data class PotentialFriendVO(
    val userId: String,
    val nickname: String,
    val avatar: String?,
    val commonInterests: List<String>?,
    val reason: String // e.g. "都在练胸肌"
)