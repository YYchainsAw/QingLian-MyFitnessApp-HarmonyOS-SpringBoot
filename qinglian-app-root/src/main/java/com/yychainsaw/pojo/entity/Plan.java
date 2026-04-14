package com.yychainsaw.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("plans")
@Schema(description = "Plan模型")
public class Plan {

    @TableId(value = "plan_id", type = IdType.AUTO)
    @Schema(description = "planId")
    private Long planId;

    @TableField("user_id")
    @Schema(description = "userId")
    private UUID userId;

    @Schema(description = "title")
    private String title;

    @Schema(description = "description")
    private String description;

    @TableField("start_date")
    @Schema(description = "startDate")
    private LocalDate startDate;

    @TableField("end_date")
    @Schema(description = "endDate")
    private LocalDate endDate;

    @Schema(description = "status")
    private String status;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @Schema(description = "createdAt")
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "updatedAt")
    private LocalDateTime updatedAt;
}
