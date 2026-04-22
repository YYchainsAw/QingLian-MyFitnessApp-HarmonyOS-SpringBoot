package com.yychainsaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yychainsaw.mapper.PostMapper;
import com.yychainsaw.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
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
        // 1. 优先处理遗留的 syncing 数据（防止 crash 后 rename 导致数据覆盖丢失）
        if (Boolean.TRUE.equals(redisTemplate.hasKey(LIKES_SYNCING_KEY))) {
            processSyncingData();
        }

        // 2. 将 buffer 重命名为 syncing 并处理
        if (Boolean.TRUE.equals(redisTemplate.hasKey(LIKES_BUFFER_KEY))) {
            try {
                redisTemplate.rename(LIKES_BUFFER_KEY, LIKES_SYNCING_KEY);
                processSyncingData();
            } catch (Exception e) {
                log.warn("Rename buffer key failed (maybe concurrent execution)", e);
            }
        }
    }

    private void processSyncingData() {
        // 使用 scan 替代 entries，避免一次性加载大量数据导致 OOM
        try (Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(LIKES_SYNCING_KEY, ScanOptions.NONE)) {
            while (cursor.hasNext()) {
                Map.Entry<Object, Object> entry = cursor.next();
                try {
                    Long postId = Long.valueOf(entry.getKey().toString());
                    int delta = Integer.parseInt(entry.getValue().toString());

                    if (delta == 0) continue;

                    UpdateWrapper<Post> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("post_id", postId)
                            .setSql("likes_count = likes_count + " + delta);

                    postMapper.update(null, updateWrapper);
                } catch (Exception e) {
                    log.error("同步帖子 {} 点赞数失败", entry.getKey(), e);
                }
            }
            redisTemplate.delete(LIKES_SYNCING_KEY);
        } catch (Exception e) {
            log.error("处理点赞同步数据异常", e);
        }
    }
}
