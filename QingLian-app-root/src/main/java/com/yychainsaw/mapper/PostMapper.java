package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yychainsaw.pojo.entity.Post;
import com.yychainsaw.pojo.vo.GenderStatVO;
import com.yychainsaw.pojo.vo.InfluencerVO;
import com.yychainsaw.pojo.vo.PostVO;
import com.yychainsaw.pojo.vo.PotentialFriendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    Page<PostVO> selectPostFeed(Page<PostVO> page);

    List<InfluencerVO> selectActiveInfluencers();

    List<PotentialFriendVO> selectPotentialFriends(@Param("userId") UUID userId);

    List<GenderStatVO> selectGenderWeightStats();
}