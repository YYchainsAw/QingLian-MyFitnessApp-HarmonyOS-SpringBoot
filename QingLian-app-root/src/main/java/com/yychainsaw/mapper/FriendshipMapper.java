package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.pojo.entity.Friendship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface FriendshipMapper extends BaseMapper<Friendship> {
    @Select("SELECT u.username, p.title, p.status, p.end_date " +
            "FROM users u " +
            "JOIN friendships f ON u.user_id = f.friend_id " +
            "JOIN plans p ON u.user_id = p.user_id " +
            "WHERE f.user_id = #{userId} AND f.status = 'ACCEPTED' AND p.status = 'ACTIVE'")
    List<Map<String, Object>> selectFriendsActivePlans(UUID userId);

    // 新增：活跃度排行榜 (SQL #15)
    @Select("SELECT u.username, COUNT(wr.record_id) AS total_workouts, MAX(wr.workout_date) AS last_workout " +
            "FROM users u " +
            "JOIN friendships f ON u.user_id = f.friend_id " +
            "JOIN workout_records wr ON u.user_id = wr.user_id " +
            "WHERE f.user_id = #{userId} AND f.status = 'ACCEPTED' " +
            "GROUP BY u.user_id, u.username " +
            "ORDER BY total_workouts DESC LIMIT 5")
    List<Map<String, Object>> selectFriendRankings(UUID userId);
}
