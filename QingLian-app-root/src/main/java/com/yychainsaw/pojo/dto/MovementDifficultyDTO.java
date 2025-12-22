package com.yychainsaw.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovementDifficultyDTO {
    @NotBlank(message = "类别不能为空")
    private String category;

    @NotNull(message = "难度等级不能为空")
    private Integer difficultyLevel;
}