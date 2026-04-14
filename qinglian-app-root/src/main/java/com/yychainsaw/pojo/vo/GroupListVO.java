package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.yychainsaw.pojo.entity.ChatGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "GroupListVO模型")
public class GroupListVO extends ChatGroup {
    // 扩展两个字段供前端展示
    @Schema(description = "lastMessage")
    private String lastMessage;      // 最后一条消息内容
    private String lastMessageTime;  // 最后一条消息时间
    @Schema(description = "unreadCount")
    private Integer unreadCount;     // 未读消息数 (可选，如果需要红点)
}
