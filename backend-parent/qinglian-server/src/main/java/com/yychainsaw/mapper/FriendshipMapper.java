package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.entity.Friendship;
import com.yychainsaw.entity.User;
import com.yychainsaw.vo.FriendListVO;
import com.yychainsaw.vo.FriendPlanVO;
import com.yychainsaw.vo.FriendRankingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

@Mapper
public interface FriendshipMapper extends BaseMapper<Friendship> {

    List<FriendPlanVO> selectFriendsActivePlans(UUID userId);

    List<FriendRankingVO> selectFriendRankings(UUID userId);

    List<FriendListVO> selectFriendList(UUID userId);
}
