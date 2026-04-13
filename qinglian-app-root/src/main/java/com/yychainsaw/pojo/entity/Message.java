package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("messages")
public class Message {
    @TableId(value = "msg_id", type = IdType.AUTO)
    private Long msgId;

    @TableField("sender_id")
    private UUID senderId;

    // 修改：允许为空 (群聊时为空)，策略设为 IGNORED 以便能显式更新为 null
    @TableField(value = "receiver_id", updateStrategy = FieldStrategy.IGNORED)
    private UUID receiverId;

    // 新增：群聊ID
    @TableField(value = "group_id", updateStrategy = FieldStrategy.IGNORED)
    private Long groupId;

    private String content;

    // 注意：此字段仅对私聊有效。群聊的已读状态由 group_read_status 表管理。
    @TableField("is_read")
    private Boolean isRead;

    @TableField(value = "sent_at", fill = FieldFill.INSERT)
    private LocalDateTime sentAt;

    private String type;
}
