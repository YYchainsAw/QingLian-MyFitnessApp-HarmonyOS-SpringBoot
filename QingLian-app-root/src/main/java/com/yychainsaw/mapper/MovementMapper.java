package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.pojo.entity.Movement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovementMapper extends BaseMapper<Movement> {
}
