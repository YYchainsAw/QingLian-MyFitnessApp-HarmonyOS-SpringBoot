package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yychainsaw.config.StringArrayTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName(value = "posts", autoResultMap = true) // autoResultMap 必须开启，否则 TypeHandler 不生效
public class Post {

    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    @TableField("user_id")
    private UUID userId;

    private String content;

    // 修改点：类型改为 String[]，Handler 改为 StringArrayTypeHandler
    @TableField(value = "image_urls", typeHandler = StringArrayTypeHandler.class)
    private String[] imageUrls;

    @TableField("likes_count")
    private Integer likesCount;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    // 建议补充：帖子内容修改时需要更新时间
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
