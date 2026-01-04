package com.yychainsaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yychainsaw.mapper.PostMapper;
import com.yychainsaw.pojo.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class LikeSyncTask {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PostMapper postMapper;

    private static final String LIKES_BUFFER_KEY = "post:likes:buffer";
    private static final String LIKES_SYNCING_KEY = "post:likes:syncing";

    @Scheduled(fixedRate = 5000)
    public void syncLikesToDb() {

        if (Boolean.FALSE.equals(redisTemplate.hasKey(LIKES_BUFFER_KEY))) {
            return;
        }

        try {
            redisTemplate.rename(LIKES_BUFFER_KEY, LIKES_SYNCING_KEY);
        } catch (Exception e) {
            return;
        }

        Map<Object, Object> map = redisTemplate.opsForHash().entries(LIKES_SYNCING_KEY);

        if (map.isEmpty()) {
            return;
        }

        log.info("开始同步点赞数据，涉及帖子数量: {}", map.size());

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            try {
                Long postId = Long.valueOf(entry.getKey().toString());
                Integer delta = Integer.valueOf(entry.getValue().toString());

                UpdateWrapper<Post> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("post_id", postId)
                             .setSql("likes_count = likes_count + " + delta);
                
                postMapper.update(null, updateWrapper);
            } catch (Exception e) {
                log.error("同步帖子 {} 点赞数失败", entry.getKey(), e);
            }
        }

        redisTemplate.delete(LIKES_SYNCING_KEY);
    }
}
