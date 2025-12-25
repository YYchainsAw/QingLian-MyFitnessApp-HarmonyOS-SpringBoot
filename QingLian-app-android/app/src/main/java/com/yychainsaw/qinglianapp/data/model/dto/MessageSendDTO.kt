package com.yychainsaw.qinglianapp.data.model.dto

data class MessageSendDTO(
    val receiverId: String,
    val content: String,
    val type: String = "TEXT" // TEXT, IMAGE
)