package com.yychainsaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "MessageVO模型")
public class MessageVO {
    @Schema(description = "id")
    private Long id;             // 消息ID (用于撤回、标记已读)
    private String senderId;     // 关键：前端通过对比当前用户ID，决定气泡在左还是右
    @Schema(description = "senderName")
    private String senderName;   // 冗余字段，方便列表展示
    private String senderNickname;
    @Schema(description = "senderAvatar")
    private String senderAvatar;
    private String receiverId;
    @Schema(description = "content")
    private String content;
    private LocalDateTime sentAt; // 推荐使用时间类型，前端格式化
    @Schema(description = "isRead")
    private Boolean isRead;      // 消息状态
    private String type;
}
