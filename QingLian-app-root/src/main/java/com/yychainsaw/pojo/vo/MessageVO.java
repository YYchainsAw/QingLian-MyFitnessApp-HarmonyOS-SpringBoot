package com.yychainsaw.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageVO {
    private Long id;             // 消息ID (用于撤回、标记已读)
    private String senderId;     // 关键：前端通过对比当前用户ID，决定气泡在左还是右
    private String senderName;   // 冗余字段，方便列表展示
    private String senderNickname;
    private String senderAvatar;
    private String receiverId;
    private String content;
    private LocalDateTime sentAt; // 推荐使用时间类型，前端格式化
    private Boolean isRead;      // 消息状态
    private String type;
}
