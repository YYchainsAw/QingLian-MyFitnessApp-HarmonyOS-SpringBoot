package com.yychainsaw.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovementDTO {
    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotBlank(message = "类别不能为空")
    private String category;

    @NotNull(message = "难度等级不能为空")
    private Integer difficultyLevel;
}

