package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.MessageSendDTO;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.vo.MessageVO;
import com.yychainsaw.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // SQL #4: 发送私信 (需定义 MessageSendDTO 包含 receiverId 和 content)
    // POST /messages
    @PostMapping
    public Result sendMessage(@RequestAttribute("id") String userIdStr, 
                              @RequestBody @Validated MessageSendDTO dto) {
        UUID senderId = UUID.fromString(userIdStr);

        // 1. 业务逻辑：保存消息到数据库，并返回完整的 MessageVO (包含时间戳、ID等)
        MessageVO messageVO = messageService.sendMessage(senderId, dto);

        // 2. 实时推送：推送到指定用户的订阅路径
        // 路径格式：/user/{receiverId}/queue/messages
        // 前端订阅：/user/queue/messages (Stomp会自动处理)
        messagingTemplate.convertAndSendToUser(
                dto.getReceiverId(),
                "/queue/messages",
                messageVO
        );

        return Result.success(messageVO);
    }

    // SQL #5: 标记与某人的消息为已读
    // PUT /messages/read/{senderId}
    @PutMapping("/read/{senderId}")
    public Result markAsRead(@RequestAttribute("id") String userIdStr, 
                             @PathVariable String senderId) {
        // 将 senderId 发给当前用户(userId)的消息标记为已读
        messageService.markAsRead(UUID.fromString(senderId), UUID.fromString(userIdStr));
        return Result.success();
    }

    // SQL #6: 查询未读消息数量
    // GET /messages/unread/count
    @GetMapping("/unread/count")
    public Result<Long> getUnreadCount(@RequestAttribute("id") String userIdStr) {
        Long count = messageService.getUnreadCount(UUID.fromString(userIdStr));
        return Result.success(count);
    }

    // SQL #10: 查找特定的聊天记录
    // GET /messages/history/{friendId}
    @GetMapping("/history/{friendId}")
    public Result<List<Map<String, Object>>> getChatHistory(@RequestAttribute("id") String userIdStr, 
                                                            @PathVariable String friendId) {
        List<Map<String, Object>> history = messageService.getChatHistory(UUID.fromString(userIdStr), UUID.fromString(friendId));
        return Result.success(history);
    }
}
