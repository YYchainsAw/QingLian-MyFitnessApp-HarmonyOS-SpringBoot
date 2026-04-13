package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("workout_records")
public class WorkoutRecord {

    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    @TableField("user_id")
    private UUID userId;

    @TableField("plan_id")
    private Long planId;

    @TableField("duration_seconds")
    private Integer durationSeconds;

    @TableField("calories_burned")
    private Integer caloriesBurned;

    @TableField(value = "workout_date", fill = FieldFill.INSERT)
    private LocalDateTime workoutDate;

    private String notes;
}
