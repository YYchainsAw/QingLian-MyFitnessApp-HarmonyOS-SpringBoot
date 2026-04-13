package com.yychainsaw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yychainsaw.pojo.dto.MessageSendDTO;
import com.yychainsaw.pojo.dto.PageBean;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.entity.Message;
import com.yychainsaw.pojo.vo.MessageVO;
import com.yychainsaw.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @PostMapping
    public Result sendMessage(@RequestBody @Validated MessageSendDTO dto) {

        if (dto.getReceiverId() == null && dto.getGroupId() == null) {
            return Result.error("接收者或群组ID不能为空");
        }

        MessageVO messageVO = messageService.sendMessage(dto);

        if (dto.getGroupId() != null) {

            messagingTemplate.convertAndSend(
                    "/topic/group." + dto.getGroupId(),
                    messageVO
            );
        } else {

            messagingTemplate.convertAndSendToUser(
                    dto.getReceiverId(),
                    "/queue/messages",
                    messageVO
            );
        }

        return Result.success(messageVO);
    }

    // 标记群消息已读 (前端进入群聊页面时调用)
    @PutMapping("/group/read")
    public Result markGroupAsRead(@RequestParam Long groupId, @RequestParam Long lastMsgId) {
        messageService.markGroupAsRead(groupId, lastMsgId);
        return Result.success();
    }

    @PutMapping("/read/{senderId}")
    public Result markAsRead(@PathVariable String senderId) {
        messageService.markAsRead(UUID.fromString(senderId));
        return Result.success();
    }

    @GetMapping("/unread/count")
    public Result<Long> getUnreadCount() {
        Long count = messageService.getUnreadCount();
        return Result.success(count);
    }

    @GetMapping("/history/{friendId}")
    public Result<PageBean<MessageVO>> getChatHistory(
            @PathVariable String friendId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Message> history = messageService.getChatHistory(UUID.fromString(friendId));
        PageInfo<Message> pageInfo = new PageInfo<>(history);

        List<Message> resultList = pageInfo.getList();
        Collections.reverse(resultList);

        List<MessageVO> voList = messageService.transferToVOList(resultList);

        PageBean<MessageVO> pageBean = new PageBean<>(pageInfo.getTotal(), voList);
        return Result.success(pageBean);
    }

    @GetMapping("/groups/{groupId}/history")
    public Result<PageBean<MessageVO>> getGroupChatHistory(
            @PathVariable Long groupId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Message> history = messageService.getGroupChatHistory(groupId);
        PageInfo<Message> pageInfo = new PageInfo<>(history);

        List<Message> resultList = pageInfo.getList();
        Collections.reverse(resultList);

        List<MessageVO> voList = messageService.transferToVOList(resultList);

        PageBean<MessageVO> pageBean = new PageBean<>(pageInfo.getTotal(), voList);
        return Result.success(pageBean);
    }
}
