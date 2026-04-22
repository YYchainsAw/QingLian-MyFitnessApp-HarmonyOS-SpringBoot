package com.yychainsaw.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.*;
import com.yychainsaw.anno.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
@Schema(description = "User模型")
public class User {

    @TableId(value = "user_id", type = IdType.NONE)
    @Schema(description = "userId")
    private UUID userId;
    private String username;
    @TableField("password_hash") // 指定数据库字段名
    @Schema(description = "passwordHash")
    private String passwordHash;
    private String nickname;
    @TableField("avatar_url")
    @Schema(description = "avatarUrl")
    private String avatarUrl;
    @Gender
    @Schema(description = "gender")
    private String gender;
    @TableField("height_cm")
    @Schema(description = "height")
    private Integer height;
    @TableField("weight_kg")
    @Schema(description = "weight")
    private BigDecimal weight;
    @TableField("last_login_time")
    @Schema(description = "lastLoginTime")
    private LocalDateTime lastLoginTime;
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @Schema(description = "createdAt")
    private LocalDateTime createdAt;
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "updatedAt")
    private LocalDateTime updatedAt;


    @TableField(exist = false)
    @Schema(description = "followers")
    private Long followers;
    @TableField(exist = false)
    @Schema(description = "following")
    private Long following;
    @TableField(exist = false)
    @Schema(description = "totalMinutes")
    private Long totalMinutes;
    @TableField(exist = false)
    @Schema(description = "totalCalories")
    private Long totalCalories;
}
