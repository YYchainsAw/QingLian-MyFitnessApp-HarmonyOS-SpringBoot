package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yychainsaw.pojo.entity.Post;
import com.yychainsaw.pojo.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
    // 操作 4: 分页获取最新帖子 (关联 User 表)
    @Select("SELECT p.*, u.nickname, u.avatar_url " +
            "FROM posts p " +
            "LEFT JOIN users u ON p.user_id = u.user_id " +
            "ORDER BY p.created_at DESC")
    Page<PostVO> selectPostFeed(Page<PostVO> page);

    // 操作 11: 活跃健身达人视图 (发帖 > 10)
    @Select("SELECT u.user_id, u.nickname, u.avatar_url, " +
            "COUNT(p.post_id) as total_posts, SUM(p.likes_count) as total_likes " +
            "FROM users u " +
            "JOIN posts p ON u.user_id = p.user_id " +
            "GROUP BY u.user_id, u.nickname, u.avatar_url " +
            "HAVING COUNT(p.post_id) > 10")
    List<Map<String, Object>> selectActiveInfluencers();

    // 操作 14: 寻找潜在好友 (基于内容相似度)
    // 注意：这里使用了 Postgres 的字符串拼接 || 和子查询
    @Select("SELECT DISTINCT u.user_id, u.nickname, u.avatar_url, other_p.content as similar_content " +
            "FROM users u " +
            "JOIN posts other_p ON u.user_id = other_p.user_id " +
            "JOIN (SELECT content FROM posts WHERE user_id = #{userId} LIMIT 5) my_posts " +
            "ON other_p.content LIKE '%' || SUBSTRING(my_posts.content, 1, 5) || '%' " +
            "WHERE u.user_id != #{userId} " +
            "AND NOT EXISTS (SELECT 1 FROM friendships f WHERE (f.user_id = #{userId} AND f.friend_id = u.user_id) OR (f.user_id = u.user_id AND f.friend_id = #{userId})) " +
            "LIMIT 10")
    List<Map<String, Object>> selectPotentialFriends(@Param("userId") UUID userId);

    // 操作 15: 性别与体重分段统计
    @Select("SELECT gender, " +
            "FLOOR(weight_kg / 10) * 10 AS weight_range_start, " +
            "COUNT(DISTINCT user_id) AS user_count, " +
            "COUNT(post_id) * 1.0 / NULLIF(COUNT(DISTINCT user_id), 0) AS avg_posts " +
            "FROM (SELECT u.user_id, u.gender, u.weight_kg, p.post_id FROM users u LEFT JOIN posts p ON u.user_id = p.user_id WHERE u.weight_kg IS NOT NULL) as sub " +
            "GROUP BY gender, weight_range_start " +
            "ORDER BY gender, weight_range_start")
    List<Map<String, Object>> selectGenderWeightStats();
}