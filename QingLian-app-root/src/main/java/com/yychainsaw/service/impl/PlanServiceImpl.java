package com.yychainsaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yychainsaw.mapper.FriendshipMapper;
import com.yychainsaw.mapper.MessageMapper;
import com.yychainsaw.mapper.PlanMapper;
import com.yychainsaw.pojo.entity.Friendship;
import com.yychainsaw.pojo.entity.Message;
import com.yychainsaw.pojo.entity.Plan;
import com.yychainsaw.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private FriendshipMapper friendshipMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPlanAndNotifyFriends(UUID userId, Plan plan) {
        // 1. 插入计划
        plan.setUserId(userId);
        plan.setStatus("ACTIVE");
        planMapper.insert(plan);

        // 2. 查找所有好友 (对应 SQL 逻辑)
        LambdaQueryWrapper<Friendship> friendQuery = new LambdaQueryWrapper<>();
        friendQuery.eq(Friendship::getUserId, userId)
                   .eq(Friendship::getStatus, "ACCEPTED");
        List<Friendship> friendships = friendshipMapper.selectList(friendQuery);

        // 3. 批量发送通知消息
        // 虽然可以在 Java 中循环插入，但为了性能，也可以考虑批量插入
        // 这里演示简单的循环逻辑，替代 SQL 中的 INSERT INTO ... SELECT
        String content = "我刚刚开始了一个新计划：" + plan.getTitle() + "，一起来健身吧！";

        for (Friendship f : friendships) {
            Message msg = new Message();
            msg.setSenderId(userId);
            msg.setReceiverId(f.getFriendId());
            msg.setContent(content);
            msg.setIsRead(false);
            messageMapper.insert(msg);
        }
    }
}
