package com.yychainsaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yychainsaw.mapper.MovementMapper;
import com.yychainsaw.pojo.dto.MovementDTO;
import com.yychainsaw.pojo.dto.MovementDifficultyDTO;
import com.yychainsaw.pojo.dto.PageBean;
import com.yychainsaw.pojo.entity.Movement;
import com.yychainsaw.pojo.vo.MovementAnalyticsVO;
import com.yychainsaw.pojo.vo.MovementVO;
import com.yychainsaw.service.movementService;
import com.yychainsaw.utils.ThreadLocalUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class movementServiceImpl implements movementService {
    @Autowired
    private MovementMapper movementMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    private String getMovementCacheKey(String keyword) {
        return "movement:search:" + keyword;
    }

    @Override
    public void addMovement(MovementDTO movementDTO) {
        Movement movement = new Movement();

        movement.setTitle(movementDTO.getTitle());
        movement.setDescription(movementDTO.getDescription());
        movement.setCategory(movementDTO.getCategory());
        movement.setDifficultyLevel(movementDTO.getDifficultyLevel());

        movementMapper.insert(movement);

    }

    @Override
    public PageBean<MovementVO> search(String keyword, Integer pageNum, Integer pageSize) {
        // 构建包含分页参数的 Key 防止翻页数据重复
        String safeKeyword = (keyword == null) ? "" : keyword.trim();
        String cacheKey = "movement:search:" + safeKeyword + ":" + pageNum + ":" + pageSize;

        // 查缓存
        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isNotBlank(cacheValue)) {
            try {
                // 命中缓存直接返回
                return objectMapper.readValue(cacheValue, new TypeReference<PageBean<MovementVO>>() {});
            } catch (JsonProcessingException e) {
                // 反序列化失败（可能是旧数据格式不对），记录日志并走数据库兜底
                e.printStackTrace();
            }
        }

        //  查数据库
        PageHelper.startPage(pageNum, pageSize);

        LambdaQueryWrapper<Movement> queryWrapper = new LambdaQueryWrapper<>();
        if (!safeKeyword.isEmpty()) {
            queryWrapper.and(wrapper -> wrapper
                    .like(Movement::getTitle, safeKeyword)
                    .or()
                    .like(Movement::getDescription, safeKeyword)
            );
        }
        // 默认按热度或时间排序，防止分页数据抖动
        queryWrapper.orderByDesc(Movement::getDifficultyLevel);

        List<Movement> movements = movementMapper.selectList(queryWrapper);

        // 封装结果
        Page<Movement> p = (Page<Movement>) movements;
        PageBean<MovementVO> pageBean = new PageBean<>();
        pageBean.setTotal(p.getTotal());

        List<MovementVO> vos = movements.stream().map(m -> {
            MovementVO vo = new MovementVO();
            BeanUtils.copyProperties(m, vo);
            return vo;
        }).collect(Collectors.toList());
        pageBean.setItems(vos);

        // 写缓存
        try {
            redisTemplate.opsForValue().set(cacheKey, objectMapper.writeValueAsString(pageBean), 24 * 7, TimeUnit.HOURS);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return pageBean;
    }

    @Override
    public void changeDifficultyLevel(MovementDifficultyDTO movementDTO) {
        LambdaUpdateWrapper<Movement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Movement::getCategory, movementDTO.getCategory())
                     .set(Movement::getDifficultyLevel, movementDTO.getDifficultyLevel());

        movementMapper.update(null, updateWrapper);
    }

    @Override
    public void deleteUnusedMovement() {
        LambdaQueryWrapper<Movement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.isNull(Movement::getVideoUrl)
                    .eq(Movement::getDifficultyLevel, 0);

        movementMapper.delete(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> countCategories() {
        QueryWrapper<Movement> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("category", "COUNT(*) AS count")
                    .groupBy("category");

        return movementMapper.selectMaps(queryWrapper);
    }

    @Override
    public List<MovementVO> getHardcoreMovements() {
        LambdaQueryWrapper<Movement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Movement::getDifficultyLevel, 4);

        List<Movement> list = movementMapper.selectList(queryWrapper);

        return list.stream().map(m -> {
            MovementVO vo = new MovementVO();
            BeanUtils.copyProperties(m, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovementAnalyticsVO> getMovementAnalytics() {
        return movementMapper.getMovementAnalytics();
    }

}
