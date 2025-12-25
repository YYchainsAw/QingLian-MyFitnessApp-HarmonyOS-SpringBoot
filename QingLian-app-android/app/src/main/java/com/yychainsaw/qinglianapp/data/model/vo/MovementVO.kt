package com.yychainsaw.qinglianapp.data.model.vo

import com.google.gson.annotations.SerializedName

data class MovementVO(
    // 使用 @SerializedName 兼容驼峰(Java标准)和下划线(数据库标准)两种命名，防止解析为 null
    @SerializedName(value = "movementId", alternate = ["movement_id", "id"])
    val movementId: String = "",

    val title: String = "未知动作",
    val description: String? = null,

    @SerializedName(value = "videoUrl", alternate = ["video_url"])
    val videoUrl: String? = null,

    val category: String? = "综合",

    @SerializedName(value = "difficultyLevel", alternate = ["difficulty_level", "difficulty"])
    val difficultyLevel: Int = 1
)