package com.yychainsaw.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yychainsaw.anno.FRState;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("friendships")
@Schema(description = "Friendship模型")
public class Friendship {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "id")
    private Long id;

    @TableField("user_id")
    @Schema(description = "userId")
    private UUID userId;

    @TableField("friend_id")
    @Schema(description = "friendId")
    private UUID friendId;

    @FRState
    @Schema(description = "status")
    private String status;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @Schema(description = "createdAt")
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "updatedAt")
    private LocalDateTime updatedAt;
}
