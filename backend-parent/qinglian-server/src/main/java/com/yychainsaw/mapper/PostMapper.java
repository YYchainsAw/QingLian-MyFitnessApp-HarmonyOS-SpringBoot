package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yychainsaw.dto.PageBean;
import com.yychainsaw.entity.Post;
import com.yychainsaw.vo.GenderStatVO;
import com.yychainsaw.vo.InfluencerVO;
import com.yychainsaw.vo.PostVO;
import com.yychainsaw.vo.PotentialFriendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<PostVO> selectPostFeed();

    List<InfluencerVO> selectActiveInfluencers();

    List<PotentialFriendVO> selectPotentialFriends(@Param("userId") UUID userId);

    List<GenderStatVO> selectGenderWeightStats();
}