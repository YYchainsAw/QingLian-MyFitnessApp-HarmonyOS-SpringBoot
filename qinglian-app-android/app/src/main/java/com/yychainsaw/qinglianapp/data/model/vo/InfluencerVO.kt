package com.yychainsaw.qinglianapp.data.model.vo

data class InfluencerVO(
    val userId: String,
    val nickname: String,
    val avatar: String?,
    val followerCount: Int,
    val tags: List<String>?
)