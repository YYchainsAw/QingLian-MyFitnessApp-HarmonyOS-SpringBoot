package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.pojo.entity.WorkoutRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkoutRecordMapper extends BaseMapper<WorkoutRecord> {
    // 对应 SQL #14: 用户燃脂排行榜 (窗口函数)
    @Select("SELECT user_id, SUM(calories_burned) as total_calories, " +
            "DENSE_RANK() OVER (ORDER BY SUM(calories_burned) DESC) as rank " +
            "FROM workout_records GROUP BY user_id LIMIT 10")
    List<Map<String, Object>> selectBurnRank();
}
