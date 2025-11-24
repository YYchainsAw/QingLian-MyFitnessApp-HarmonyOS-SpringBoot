package com.yychainsaw.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Post {
    private Long postId;
    private User user;
    private String content;
    // 关键点：映射 Postgres 数组
    private List<String> imageUrls;
    private Integer likesCount = 0;
    private LocalDateTime createdAt;
}
