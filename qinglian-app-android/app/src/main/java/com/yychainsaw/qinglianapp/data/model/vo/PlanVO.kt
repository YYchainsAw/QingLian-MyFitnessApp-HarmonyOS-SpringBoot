package com.yychainsaw.qinglianapp.data.model.vo

data class PlanVO(
    val planId: Long,
    val title: String,
    val description: String?,
    val startDate: String, // 格式: "yyyy-MM-dd"
    val endDate: String,   // 格式: "yyyy-MM-dd"
    val status: String     // e.g. "ACTIVE"
)