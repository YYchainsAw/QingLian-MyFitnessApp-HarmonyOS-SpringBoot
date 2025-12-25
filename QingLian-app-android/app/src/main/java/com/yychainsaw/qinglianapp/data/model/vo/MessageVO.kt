package com.yychainsaw.qinglianapp.data.model.vo

data class MessageVO(
    val id: Long,
    val senderId: String,
    val senderName: String?,
    val receiverId: String,
    val content: String,
    val sentAt: String,
    val isRead: Boolean
)