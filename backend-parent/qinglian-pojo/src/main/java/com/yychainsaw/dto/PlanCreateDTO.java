package com.yychainsaw.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(description = "PlanCreateDTO模型")
public class PlanCreateDTO {
    @NotBlank(message = "计划标题不能为空")
    @Schema(description = "title")
    private String title;

    @Schema(description = "description")
    private String description;

    @NotNull(message = "开始日期不能为空")
    @Schema(description = "startDate")
    private LocalDate startDate;

    @NotNull(message = "结束日期不能为空")
    @Schema(description = "endDate")
    private LocalDate endDate;

    // 目标体重等其他业务字段...
    @Schema(description = "targetWeight")
    private Double targetWeight;
}
