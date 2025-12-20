package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    // SQL #1: 添加好友请求
    // POST /friendships/request?friendId=...
    @PostMapping("/request")
    public Result sendFriendRequest(@RequestAttribute("id") String userIdStr, 
                                    @RequestParam String friendId) {
        friendshipService.sendRequest(UUID.fromString(userIdStr), UUID.fromString(friendId));
        return Result.success();
    }

    // SQL #2: 接受好友请求
    // PUT /friendships/{friendId}/accept
    @PutMapping("/{friendId}/accept")
    public Result acceptFriendRequest(@RequestAttribute("id") String userIdStr, 
                                      @PathVariable String friendId) {
        friendshipService.acceptRequest(UUID.fromString(userIdStr), UUID.fromString(friendId));
        return Result.success();
    }

    // SQL #3: 删除好友关系
    // DELETE /friendships/{friendId}
    @DeleteMapping("/{friendId}")
    public Result deleteFriend(@RequestAttribute("id") String userIdStr, 
                               @PathVariable String friendId) {
        friendshipService.deleteFriend(UUID.fromString(userIdStr), UUID.fromString(friendId));
        return Result.success();
    }

    // SQL #11: 查询好友的最新健身计划
    // GET /friendships/plans
    @GetMapping("/plans")
    public Result<List<Map<String, Object>>> getFriendsActivePlans(@RequestAttribute("id") String userIdStr) {
        List<Map<String, Object>> plans = friendshipService.getFriendsActivePlans(UUID.fromString(userIdStr));
        return Result.success(plans);
    }

    // SQL #15: 活跃度排行榜 (好友圈)
    // GET /friendships/rankings
    @GetMapping("/rankings")
    public Result<List<Map<String, Object>>> getFriendRankings(@RequestAttribute("id") String userIdStr) {
        List<Map<String, Object>> rankings = friendshipService.getFriendRankings(UUID.fromString(userIdStr));
        return Result.success(rankings);
    }
}
