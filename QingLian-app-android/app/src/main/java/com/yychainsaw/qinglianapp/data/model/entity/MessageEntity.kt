package com.yychainsaw.qinglianapp.data.model.entity

data class MessageEntity(
    val msgId: Long,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val isRead: Boolean,
    val sentAt: String
)