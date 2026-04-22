package com.yychainsaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "FriendListVO模型")
public class FriendListVO {
    @Schema(description = "userId")
    private UUID userId;
    private String username;
    @Schema(description = "nickname")
    private  String nickname;
    private String avatarUrl;
    
    // 新增字段：用于前端展示会话状态
    @Schema(description = "lastMessage")
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    @Schema(description = "unreadCount")
    private Integer unreadCount;
}
