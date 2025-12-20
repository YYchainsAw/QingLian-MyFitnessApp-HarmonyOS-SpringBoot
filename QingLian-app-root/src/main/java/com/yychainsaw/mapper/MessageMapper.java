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
    // SQL #10: 查找特定聊天记录
    @Select("SELECT * FROM messages " +
            "WHERE (sender_id = #{userId} AND receiver_id = #{friendId}) " +
            "   OR (sender_id = #{friendId} AND receiver_id = #{userId}) " +
            "ORDER BY sent_at DESC LIMIT 10")
    List<Map<String, Object>> selectChatHistory(UUID userId, UUID friendId);
}
