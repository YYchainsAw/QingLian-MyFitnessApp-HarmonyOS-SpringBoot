package com.yychainsaw.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Long msgId;
    private User sender;
    private User receiver;
    private String content;
    private Boolean isRead = false;
    private LocalDateTime sentAt;
}
