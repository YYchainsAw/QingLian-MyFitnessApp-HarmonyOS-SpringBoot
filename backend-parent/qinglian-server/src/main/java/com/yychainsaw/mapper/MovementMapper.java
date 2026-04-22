package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.dto.MovementDTO;
import com.yychainsaw.dto.MovementDifficultyDTO;
import com.yychainsaw.entity.Movement;
import com.yychainsaw.vo.MovementAnalyticsVO;
import com.yychainsaw.vo.MovementVO;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MovementMapper extends BaseMapper<Movement> {

    List<MovementAnalyticsVO> getMovementAnalytics();
}
