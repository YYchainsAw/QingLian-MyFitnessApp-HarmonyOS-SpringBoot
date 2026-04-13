package com.yychainsaw.pojo.vo;

import lombok.Data;

@Data
public class UserSocialDashboardVO {
    private Integer friendCount;     // 好友数量
    private Integer ranking;         // 排名
    private Integer unreadMessages;  // 未读消息
    // 其他你需要查询的字段...
}
