package com.yychainsaw.service;

import com.yychainsaw.dto.MessageSendDTO;
import com.yychainsaw.entity.Message;
import com.yychainsaw.vo.MessageVO;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageVO sendMessage(MessageSendDTO dto);

    void markAsRead(UUID senderId);

    Long getUnreadCount();

    List<Message> getChatHistory(UUID uuid1);

    void markGroupAsRead(Long groupId, Long lastMsgId);

    List<Message> getGroupChatHistory(Long groupId);

    List<MessageVO> transferToVOList(List<Message> messages);
}
