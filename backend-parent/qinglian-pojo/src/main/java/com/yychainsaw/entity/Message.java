package com.yychainsaw.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("messages")
@Schema(description = "Message模型")
public class Message {
    @TableId(value = "msg_id", type = IdType.AUTO)
    @Schema(description = "msgId")
    private Long msgId;

    @TableField("sender_id")
    @Schema(description = "senderId")
    private UUID senderId;

    // 修改：允许为空 (群聊时为空)，策略设为 IGNORED 以便能显式更新为 null
    @TableField(value = "receiver_id", updateStrategy = FieldStrategy.IGNORED)
    @Schema(description = "receiverId")
    private UUID receiverId;

    // 新增：群聊ID
    @TableField(value = "group_id", updateStrategy = FieldStrategy.IGNORED)
    @Schema(description = "groupId")
    private Long groupId;

    @Schema(description = "content")
    private String content;

    // 注意：此字段仅对私聊有效。群聊的已读状态由 group_read_status 表管理。
    @TableField("is_read")
    @Schema(description = "isRead")
    private Boolean isRead;

    @TableField(value = "sent_at", fill = FieldFill.INSERT)
    @Schema(description = "sentAt")
    private LocalDateTime sentAt;

    @Schema(description = "type")
    private String type;
}
