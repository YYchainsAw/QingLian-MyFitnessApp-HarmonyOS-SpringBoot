package com.yychainsaw.pojo.vo;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PostVO {
    private Long postId;
    private UUID userId;
    private String nickname;   // 关联查询出来的用户名
    private String avatarUrl;  // 关联查询出来的头像
    private String content;
    private String[] imageUrls; // 对应实体类的 String[]
    private Integer likesCount;
    private LocalDateTime createdAt;
}
