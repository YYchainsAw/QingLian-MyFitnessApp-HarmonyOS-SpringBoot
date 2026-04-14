package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Schema(description = "WorkoutRecordDTO模型")
public class WorkoutRecordDTO {
    @NotNull(message = "运动记录不能为空")
    @Schema(description = "durationSeconds")
    private Integer durationSeconds;
    @NotNull(message = "运动记录不能为空")
    @Schema(description = "caloriesBurned")
    private Integer caloriesBurned;

    @Schema(description = "notes")
    private String notes;
}
