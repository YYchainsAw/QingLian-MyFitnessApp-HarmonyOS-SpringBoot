package com.yychainsaw.service;

import com.yychainsaw.pojo.vo.FriendPlanVO;
import com.yychainsaw.pojo.vo.FriendRankingVO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FriendshipService {
    void sendRequest(UUID friendId);

    void acceptRequest(UUID friendId);

    void deleteFriend(UUID friendId);

    List<FriendPlanVO> getFriendsActivePlans();

    List<FriendRankingVO> getFriendRankings();
}
