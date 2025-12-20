package com.yychainsaw.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FriendshipService {
    void sendRequest(UUID userId, UUID friendId);

    void acceptRequest(UUID userId, UUID friendId);

    void deleteFriend(UUID userId, UUID friendId);

    List<Map<String, Object>> getFriendsActivePlans(UUID userId);

    List<Map<String, Object>> getFriendRankings(UUID userId);
}
