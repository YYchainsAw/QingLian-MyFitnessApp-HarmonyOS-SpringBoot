package com.yychainsaw.qinglianapp.data.model.dto

data class WorkoutRecordDTO(
    val durationSeconds: Int,
    val caloriesBurned: Int,
    val notes: String?,
    val workoutDate: String, // 格式: "yyyy-MM-dd HH:mm:ss"
    val planId: Long? = null
)