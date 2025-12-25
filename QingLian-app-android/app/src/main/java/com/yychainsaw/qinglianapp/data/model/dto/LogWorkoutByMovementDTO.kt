package com.yychainsaw.qinglianapp.data.model.dto

data class LogWorkoutByMovementDTO(
    val movementId: String,
    val sets: Int,
    val reps: Int,
    val weight: Double
)