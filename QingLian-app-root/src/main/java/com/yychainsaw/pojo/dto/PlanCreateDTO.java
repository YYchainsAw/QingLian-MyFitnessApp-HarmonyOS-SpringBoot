package com.yychainsaw.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PlanCreateDTO {
    @NotBlank(message = "计划标题不能为空")
    private String title;

    private String description;

    @NotNull(message = "开始日期不能为空")
    private LocalDate startDate;

    @NotNull(message = "结束日期不能为空")
    private LocalDate endDate;

    // 目标体重等其他业务字段...
    private Double targetWeight;
}
