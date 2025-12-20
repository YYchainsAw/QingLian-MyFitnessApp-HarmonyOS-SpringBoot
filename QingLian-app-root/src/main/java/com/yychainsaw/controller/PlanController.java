package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.PlanCreateDTO;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    /**
     * 创建健身计划
     * 业务逻辑：创建计划记录 -> 查找双向好友 -> 发送站内信通知
     */
    @PostMapping
    public Result createPlan(@RequestAttribute("id") String userIdStr,
                             @RequestBody @Validated PlanCreateDTO dto) {
        // 1. 将拦截器传入的 String 类型 ID 转为 UUID
        UUID userId = UUID.fromString(userIdStr);

        // 2. 调用 Service 执行核心业务
        planService.createPlanAndNotifyFriends(userId, dto);

        // 3. 返回成功响应
        return Result.success();
    }


    // SQL #8: 查询用户的活跃计划
    // GET /plans/active
    @GetMapping("/active")
    public Result<List<Map<String, Object>>> getActivePlans(@RequestAttribute("id") String userIdStr) {
        // 这里的返回值建议封装为 PlanVO，这里暂时用 Map 对应 SQL 结果
        List<Map<String, Object>> plans = planService.getActivePlans(UUID.fromString(userIdStr));
        return Result.success(plans);
    }

    // SQL #9: 修改计划状态为完成
    // PUT /plans/{planId}/complete
    @PutMapping("/{planId}/complete")
    public Result completePlan(@PathVariable Long planId) {
        planService.completePlan(planId);
        return Result.success();
    }
}
