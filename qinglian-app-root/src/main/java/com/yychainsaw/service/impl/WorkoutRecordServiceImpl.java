package com.yychainsaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yychainsaw.mapper.MovementMapper;
import com.yychainsaw.mapper.WorkoutRecordMapper;
import com.yychainsaw.pojo.dto.LogWorkoutByMovementDTO;
import com.yychainsaw.pojo.dto.WorkoutRecordDTO;
import com.yychainsaw.pojo.entity.Movement;
import com.yychainsaw.pojo.entity.WorkoutRecord;
import com.yychainsaw.service.WorkoutRecordService;
import com.yychainsaw.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class WorkoutRecordServiceImpl implements WorkoutRecordService {

    @Autowired
    private WorkoutRecordMapper workoutRecordMapper;
    @Autowired
    private MovementMapper movementMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private String getTodayCaloriesKey(UUID userId) {
        return "user:calories:today:" + userId;
    }

    @Override
    public void addWorkoutRecord(WorkoutRecordDTO dto) {
        UUID userId = ThreadLocalUtil.getCurrentUserId();

        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.setUserId(userId);
        workoutRecord.setDurationSeconds(dto.getDurationSeconds());
        workoutRecord.setCaloriesBurned(dto.getCaloriesBurned());
        workoutRecord.setNotes(dto.getNotes());

        workoutRecordMapper.insert(workoutRecord);

        redisTemplate.delete(getTodayCaloriesKey(userId));
    }

    @Override
    public List<WorkoutRecord> getWorkoutHistory() {
        UUID userId = ThreadLocalUtil.getCurrentUserId();

        LocalDateTime sevenDayAgo = LocalDateTime.now().minusDays(7);

        LambdaQueryWrapper<WorkoutRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkoutRecord::getUserId, userId)
                    .ge(WorkoutRecord::getWorkoutDate, sevenDayAgo)
                    .orderByDesc(WorkoutRecord::getWorkoutDate);


        return workoutRecordMapper.selectList(queryWrapper);
    }

    @Override
    public void updateCalories(Long recordId, Integer calories) {
        // Item 8: Correct calories
        LambdaUpdateWrapper<WorkoutRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(WorkoutRecord::getRecordId, recordId)
                .set(WorkoutRecord::getCaloriesBurned, calories);
        workoutRecordMapper.update(null, updateWrapper);

        UUID userId = ThreadLocalUtil.getCurrentUserId();
        redisTemplate.delete(getTodayCaloriesKey(userId));
    }

    @Override
    public void deleteInvalidRecords() {
        // Item 9: Delete records < 60s
        LambdaQueryWrapper<WorkoutRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.lt(WorkoutRecord::getDurationSeconds, 60);
        workoutRecordMapper.delete(queryWrapper);
    }

    @Override
    public Integer getTodayCalories() {
        // Item 10: Today's total calories
        UUID userId = ThreadLocalUtil.getCurrentUserId();
        String key = getTodayCaloriesKey(userId);

        String cacheValue = redisTemplate.opsForValue().get(key);
        if (cacheValue != null) {
            try {
                return Integer.parseInt(cacheValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        QueryWrapper<WorkoutRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("COALESCE(SUM(calories_burned), 0) as total")
                .eq("user_id", userId)
                // PostgreSQL specific date function
                .apply("DATE(workout_date) = CURRENT_DATE");

        Map<String, Object> result = workoutRecordMapper.selectMaps(queryWrapper).stream().findFirst().orElse(null);

        Integer totalCalories = 0;
        if (result != null && result.get("total") != null) {
            Object totalObj = result.get("total");
            if (totalObj instanceof BigDecimal) {
                totalCalories = ((BigDecimal) totalObj).intValue();
            } else {
                totalCalories = Integer.parseInt(totalObj.toString());
            }
        }

        long secondsUntilMidnight = Duration.between(LocalDateTime.now(), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIN)).getSeconds();
        if (secondsUntilMidnight > 0) {
            redisTemplate.opsForValue().set(key, String.valueOf(totalCalories), secondsUntilMidnight, TimeUnit.SECONDS);
        }

        return totalCalories;
    }

    @Override
    public void logWorkoutByMovement(LogWorkoutByMovementDTO dto) {
        // Item 11: Auto-generate record based on movement difficulty
        UUID userId = ThreadLocalUtil.getCurrentUserId();

        // 1. Get movement info
        Movement movement = movementMapper.selectById(dto.getMovementId());
        if (movement == null) {
            throw new RuntimeException("动作不存在");
        }

        // 2. Calculate calories
        int difficulty = movement.getDifficultyLevel() != null ? movement.getDifficultyLevel() : 1;
        int calories = (int) (dto.getDurationSeconds() * difficulty * 0.1);

        // 3. Insert record
        WorkoutRecord record = new WorkoutRecord();
        record.setUserId(userId);
        record.setDurationSeconds(dto.getDurationSeconds());
        record.setCaloriesBurned(calories);
        record.setNotes("专项训练: " + movement.getTitle() + ". " + (dto.getNotes() != null ? dto.getNotes() : ""));

        workoutRecordMapper.insert(record);

        redisTemplate.delete(getTodayCaloriesKey(userId));
    }

    @Override
    public List<Map<String, Object>> getLeaderboard() {
        // Item 14: Leaderboard
        // Note: MP selectMaps doesn't support window functions easily in wrapper without custom SQL string
        // We use a custom query wrapper with select string
        QueryWrapper<WorkoutRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id",
                        "SUM(calories_burned) as total_calories",
                        "DENSE_RANK() OVER (ORDER BY SUM(calories_burned) DESC) as rank")
                .groupBy("user_id")
                .last("LIMIT 10");

        return workoutRecordMapper.selectMaps(queryWrapper);
    }
}
