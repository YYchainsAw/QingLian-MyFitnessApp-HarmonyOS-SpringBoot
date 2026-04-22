package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.entity.WorkoutRecord;
import com.yychainsaw.vo.BurnRankVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkoutRecordMapper extends BaseMapper<WorkoutRecord> {

    List<BurnRankVO> selectBurnRank();
}
