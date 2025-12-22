package com.yychainsaw.pojo.vo;

import lombok.Data;

import java.util.UUID;

@Data
public class PotentialFriendVO {
    private UUID userId;
    private String nickname;
    private String avatarUrl;
    private String similarContent; // 相似的帖子内容片段
}