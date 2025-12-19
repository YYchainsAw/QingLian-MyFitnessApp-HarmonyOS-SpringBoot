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

    @TableField("receiver_id")
    private UUID receiverId;

    private String content;

    @TableField("is_read")
    private Boolean isRead;

    @TableField(value = "sent_at", fill = FieldFill.INSERT)
    private LocalDateTime sentAt;
}
