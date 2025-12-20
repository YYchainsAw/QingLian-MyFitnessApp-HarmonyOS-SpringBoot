package com.yychainsaw.service;

import com.yychainsaw.pojo.dto.MessageSendDTO;
import com.yychainsaw.pojo.vo.MessageVO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MessageService {
    MessageVO sendMessage(UUID uuid, MessageSendDTO dto);

    void markAsRead(UUID uuid, UUID uuid1);

    Long getUnreadCount(UUID uuid);

    List<Map<String, Object>> getChatHistory(UUID uuid, UUID uuid1);
}
