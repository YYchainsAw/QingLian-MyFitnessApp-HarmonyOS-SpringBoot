package com.yychainsaw.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class WorkoutRecordDTO {
    @NotNull(message = "运动记录不能为空")
    private Integer durationSeconds;
    @NotNull(message = "运动记录不能为空")
    private Integer caloriesBurned;

    private String notes;
}
