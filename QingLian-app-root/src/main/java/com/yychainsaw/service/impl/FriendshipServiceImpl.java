package com.yychainsaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yychainsaw.mapper.FriendshipMapper;
import com.yychainsaw.pojo.entity.Friendship;
import com.yychainsaw.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Autowired
    private FriendshipMapper friendshipMapper;

    @Override
    public void sendRequest(UUID userId, UUID friendId) {
        Friendship friendship = new Friendship();
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);
        friendship.setStatus("PENDING");
        friendshipMapper.insert(friendship);
    }

    @Override
    public void acceptRequest(UUID userId, UUID friendId) {
        // 更新状态为 ACCEPTED
        QueryWrapper<Friendship> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("friend_id", friendId);

        Friendship friendship = new Friendship();
        friendship.setStatus("ACCEPTED");
        friendshipMapper.update(friendship, wrapper);
    }

    @Override
    public void deleteFriend(UUID userId, UUID friendId) {
        // 双向删除 (SQL #3)
        QueryWrapper<Friendship> wrapper = new QueryWrapper<>();
        wrapper.and(w -> w
                .nested(i -> i.eq("user_id", userId).eq("friend_id", friendId))
                .or()
                .nested(i -> i.eq("user_id", friendId).eq("friend_id", userId))
        );
        friendshipMapper.delete(wrapper);
    }

    @Override
    public List<Map<String, Object>> getFriendsActivePlans(UUID userId) {
        return friendshipMapper.selectFriendsActivePlans(userId);
    }

    @Override
    public List<Map<String, Object>> getFriendRankings(UUID userId) {
        return friendshipMapper.selectFriendRankings(userId);
    }
}
