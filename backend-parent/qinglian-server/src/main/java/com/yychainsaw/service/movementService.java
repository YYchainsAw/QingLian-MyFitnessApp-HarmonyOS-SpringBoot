package com.yychainsaw.service;

import com.yychainsaw.dto.MovementDTO;
import com.yychainsaw.dto.MovementDifficultyDTO;
import com.yychainsaw.dto.PageBean;
import com.yychainsaw.vo.MovementAnalyticsVO;
import com.yychainsaw.vo.MovementVO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

public interface movementService {
    void addMovement(MovementDTO movementDTO);

    @Cacheable(value = "movements", key = "#keyword + '_' + #pageNum", unless = "#result == null")
    PageBean<MovementVO> search(String keyword, Integer pageNum, Integer pageSize);

    void changeDifficultyLevel(MovementDifficultyDTO movementDTO);

    void deleteUnusedMovement();

    @Cacheable(value = "movement_categories")
    List<Map<String, Object>> countCategories();

    List<MovementVO> getHardcoreMovements();

    List<MovementAnalyticsVO> getMovementAnalytics();
}
