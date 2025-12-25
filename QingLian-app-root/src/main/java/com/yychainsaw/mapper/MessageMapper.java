package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.pojo.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> selectChatHistory(UUID userId, UUID friendId);

    void markGroupAsRead(Long groupId, UUID userId, Long lastMsgId);

    Long countTotalUnread(UUID userId);
}
