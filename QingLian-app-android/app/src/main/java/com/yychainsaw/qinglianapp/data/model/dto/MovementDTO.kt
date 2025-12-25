package com.yychainsaw.qinglianapp.data.model.dto

data class MovementDTO(
    val name: String,
    val description: String?,
    val difficulty: Int, // 1-5
    val category: String,
    val imageUrl: String?
)