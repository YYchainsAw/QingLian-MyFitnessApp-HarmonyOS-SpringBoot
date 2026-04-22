package com.yychainsaw.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("workout_records")
@Schema(description = "WorkoutRecord模型")
public class WorkoutRecord {

    @TableId(value = "record_id", type = IdType.AUTO)
    @Schema(description = "recordId")
    private Long recordId;

    @TableField("user_id")
    @Schema(description = "userId")
    private UUID userId;

    @TableField("plan_id")
    @Schema(description = "planId")
    private Long planId;

    @TableField("duration_seconds")
    @Schema(description = "durationSeconds")
    private Integer durationSeconds;

    @TableField("calories_burned")
    @Schema(description = "caloriesBurned")
    private Integer caloriesBurned;

    @TableField(value = "workout_date", fill = FieldFill.INSERT)
    @Schema(description = "workoutDate")
    private LocalDateTime workoutDate;

    @Schema(description = "notes")
    private String notes;
}
