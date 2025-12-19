package com.yychainsaw.service;

import com.yychainsaw.pojo.dto.PlanCreateDTO;
import java.util.UUID;

public interface PlanService {
    void createPlanAndNotifyFriends(UUID userId, PlanCreateDTO dto);
}
