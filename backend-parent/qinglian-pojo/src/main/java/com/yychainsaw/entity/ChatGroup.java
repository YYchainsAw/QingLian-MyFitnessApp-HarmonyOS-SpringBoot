package com.yychainsaw.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("chat_groups")
@Schema(description = "ChatGroup模型")
public class ChatGroup {
    @TableId(value = "group_id", type = IdType.AUTO)
    @Schema(description = "groupId")
    private Long groupId;

    @Schema(description = "name")
    private String name;
    private UUID ownerId;
    @Schema(description = "avatarUrl")
    private String avatarUrl;
    private String notice;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "createdAt")
    private LocalDateTime createdAt;
}
