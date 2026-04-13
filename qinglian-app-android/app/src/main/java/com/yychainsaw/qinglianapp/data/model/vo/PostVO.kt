package com.yychainsaw.qinglianapp.data.model.vo

import com.google.gson.annotations.SerializedName

data class PostVO(
    // 兼容 id, postId, post_id
    @SerializedName("id", alternate = ["postId", "post_id"])
    val postId: String = "",

    // 兼容 nickname, username, authorName
    @SerializedName("nickname", alternate = ["username", "authorName", "author"])
    val authorName: String = "Unknown",

    // 修复：添加 avatar_url 映射
    @SerializedName("avatar", alternate = ["avatarUrl", "headImg", "authorAvatar", "avatar_url"])
    val authorAvatar: String? = null,

    val content: String = "",

    // 修复：添加 image_urls 映射 (这是图片不显示的关键)
    @SerializedName("images", alternate = ["imgs", "imageUrls", "imgUrls", "image_urls"])
    val imageUrls: List<String>? = null,

    // 修复：添加 likes_count 映射 (这是点赞数不显示的关键)
    @SerializedName("likeCount", alternate = ["likesCount", "likes_count", "like_count"])
    val likeCount: Int = 0,

    // 新增：当前用户是否已点赞
    @SerializedName("isLiked", alternate = ["is_liked"])
    val isLiked: Boolean = false,

    // 修复：添加 created_at 映射
    @SerializedName("createTime", alternate = ["create_time", "createdAt", "created_at"])
    val createTime: String = ""
)
