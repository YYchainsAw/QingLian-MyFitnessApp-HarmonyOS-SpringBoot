package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@TableName(value = "posts", autoResultMap = true) // autoResultMap 必须开启，否则 TypeHandler 不生效
public class Post {

    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    @TableField("user_id")
    private UUID userId;

    private String content;

    // 使用 JacksonTypeHandler 自动将 List<String> 转为 JSON 字符串存入数据库
    @TableField(value = "image_urls", typeHandler = JacksonTypeHandler.class)
    private List<String> imageUrls;

    @TableField("likes_count")
    private Integer likesCount;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
