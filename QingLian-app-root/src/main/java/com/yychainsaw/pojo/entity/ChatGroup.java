package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("chat_groups")
public class ChatGroup {
    @TableId(value = "group_id", type = IdType.AUTO)
    private Long groupId;

    private String name;
    private UUID ownerId;
    private String avatarUrl;
    private String notice;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
