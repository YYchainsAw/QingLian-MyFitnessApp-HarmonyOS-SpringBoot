package com.yychainsaw.qinglianapp.data.model.vo

data class MessageVO(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val type: String,
    val createTime: String,
    val isRead: Boolean
)