package com.yychainsaw.qinglianapp.data.model.dto

data class WorkoutRecordDTO(
    val id: String,
    val date: String,
    val duration: Int,
    val calories: Int,
    val items: List<String>? // 简化的记录项
)