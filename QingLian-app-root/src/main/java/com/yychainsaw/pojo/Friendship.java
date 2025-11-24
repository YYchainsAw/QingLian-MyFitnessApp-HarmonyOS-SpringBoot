package com.yychainsaw.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Friendship {

    private FriendshipId id;
    // 映射关系以便查询详细信息 (insertable=false, updatable=false 防止重复映射)
    private User user;
    private User friend;
    private String status; // PENDING, ACCEPTED
    private LocalDateTime createdAt;
}
