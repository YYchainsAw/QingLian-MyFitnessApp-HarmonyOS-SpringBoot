package com.yychainsaw.service;

import com.yychainsaw.pojo.entity.Plan;
import java.util.UUID;

public interface PlanService {
    void createPlanAndNotifyFriends(UUID userId, Plan plan);
}
