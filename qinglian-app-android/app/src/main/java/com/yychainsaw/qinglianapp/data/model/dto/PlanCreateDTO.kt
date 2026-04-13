package com.yychainsaw.qinglianapp.data.model.dto

data class PlanCreateDTO(
    val name: String,
    val goal: String?,
    val startDate: String,
    val endDate: String
)