package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.yychainsaw.dto.PlanCreateDTO;
import com.yychainsaw.dto.Result;
import com.yychainsaw.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Plan管理", description = "Plan相关的API接口")
@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    /**
     * 创建健身计划
     * 业务逻辑：创建计划记录 -> 查找双向好友 -> 发送站内信通知
     */
    @Operation(summary = "createPlan", description = "createPlan 接口")
    @PostMapping
    public Result createPlan(@RequestBody @Validated PlanCreateDTO dto) {

        planService.createPlanAndNotifyFriends(dto);

        return Result.success();
    }

    @Operation(summary = "接口", description = "接口 接口")
    @GetMapping("/active")
    public Result<List<Map<String, Object>>> getActivePlans() {

        List<Map<String, Object>> plans = planService.getActivePlans();
        return Result.success(plans);
    }

    @Operation(summary = "completePlan", description = "completePlan 接口")
    @PutMapping("/{planId}/complete")
    public Result completePlan(@PathVariable Long planId) {
        planService.completePlan(planId);
        return Result.success();
    }
}
