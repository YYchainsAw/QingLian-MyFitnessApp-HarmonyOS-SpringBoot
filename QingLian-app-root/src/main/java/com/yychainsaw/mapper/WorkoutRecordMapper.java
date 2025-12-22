package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.pojo.entity.WorkoutRecord;
import com.yychainsaw.pojo.vo.BurnRankVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkoutRecordMapper extends BaseMapper<WorkoutRecord> {

    List<BurnRankVO> selectBurnRank();
}
