package com.yychainsaw.pojo.vo;

import lombok.Data;
import java.util.UUID;


@Data
public class InfluencerVO {
    private UUID userId;
    private String nickname;
    private String avatarUrl;
    private Long totalPosts;
    private Long totalLikes;
}


