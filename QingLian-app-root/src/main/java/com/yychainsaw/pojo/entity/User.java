package com.yychainsaw.pojo.entity;

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
public class User {

    @TableId(value = "user_id", type = IdType.NONE)
    private UUID userId;
    private String username;
    @TableField("password_hash") // 指定数据库字段名
    private String passwordHash;
    private String nickname;
    @TableField("avatar_url")
    private String avatarUrl;
    @Gender
    private String gender;
    @TableField("height_cm")
    private Integer height;
    @TableField("weight_kg")
    private BigDecimal weight;
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


    @TableField(exist = false)
    private Long followers;
    @TableField(exist = false)
    private Long following;
    @TableField(exist = false)
    private Long totalMinutes;
    @TableField(exist = false)
    private Long totalCalories;
}
