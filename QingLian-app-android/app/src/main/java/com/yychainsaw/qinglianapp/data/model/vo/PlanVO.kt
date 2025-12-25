package com.yychainsaw.qinglianapp.data.model.vo

data class PlanVO(
    val planId: String,
    val name: String,
    val status: String, // ACTIVE, COMPLETED
    val progress: Int
)