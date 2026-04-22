package com.yychainsaw.service;

import com.yychainsaw.dto.PlanCreateDTO;

import java.util.List;
import java.util.Map;

public interface PlanService {
    void createPlanAndNotifyFriends(PlanCreateDTO dto);

    List<Map<String, Object>> getActivePlans();

    void completePlan(Long planId);
}
