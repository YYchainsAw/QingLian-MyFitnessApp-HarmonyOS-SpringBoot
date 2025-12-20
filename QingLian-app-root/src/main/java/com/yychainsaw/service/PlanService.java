package com.yychainsaw.service;

import com.yychainsaw.pojo.dto.PlanCreateDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PlanService {
    void createPlanAndNotifyFriends(UUID userId, PlanCreateDTO dto);

    List<Map<String, Object>> getActivePlans(UUID userId);

    void completePlan(Long planId);
}
