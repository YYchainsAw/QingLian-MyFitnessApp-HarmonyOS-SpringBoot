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
}
