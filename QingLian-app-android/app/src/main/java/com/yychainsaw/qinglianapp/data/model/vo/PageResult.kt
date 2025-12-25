package com.yychainsaw.qinglianapp.data.model.vo

// 修改后的 PageResult：增加默认值，防止 JSON 解析崩溃
data class PageResult<T>(
    val content: List<T>? = emptyList(), // 允许为 null，默认为空列表
    val totalElements: Long = 0,
    val totalPages: Int = 0,
    val size: Int = 0,
    val number: Int = 0
)