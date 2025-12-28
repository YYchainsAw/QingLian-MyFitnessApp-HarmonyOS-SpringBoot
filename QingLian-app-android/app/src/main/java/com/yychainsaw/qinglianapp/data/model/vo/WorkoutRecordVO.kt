package com.yychainsaw.qinglianapp.data.model.vo

data class WorkoutRecordVO(
    val id: Long,
    val durationSeconds: Int,
    val caloriesBurned: Int,
    val notes: String?,
    val workoutDate: String,
    val planName: String? = null
)