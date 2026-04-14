package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.entity.User;
import com.yychainsaw.pojo.vo.FriendListVO;
import com.yychainsaw.pojo.vo.FriendPlanVO;
import com.yychainsaw.pojo.vo.FriendRankingVO;
import com.yychainsaw.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Friendship管理", description = "Friendship相关的API接口")
@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @Operation(summary = "getFriendList", description = "getFriendList 接口")
    @GetMapping
    public Result<List<FriendListVO>> getFriendList() {
        List<FriendListVO> friends = friendshipService.getFriendList();
        return Result.success(friends);
    }

    @Operation(summary = "sendFriendRequest", description = "sendFriendRequest 接口")
    @PostMapping("/request")
    public Result sendFriendRequest(@RequestParam String friendId) {
        friendshipService.sendRequest(UUID.fromString(friendId));
        return Result.success();
    }

    @Operation(summary = "getFriendRequestList", description = "getFriendRequestList 接口")
    @GetMapping("/request/pending")
    public Result<List<FriendListVO>> getFriendRequestList() {
        return Result.success(friendshipService.getPendingRequests());
    }

    @Operation(summary = "acceptFriendRequest", description = "acceptFriendRequest 接口")
    @PutMapping("/{friendId}/accept")
    public Result acceptFriendRequest(@PathVariable String friendId) {
        friendshipService.acceptRequest(UUID.fromString(friendId));
        return Result.success();
    }

    @Operation(summary = "deleteFriend", description = "deleteFriend 接口")
    @DeleteMapping("/{friendId}")
    public Result deleteFriend(@PathVariable String friendId) {
        friendshipService.deleteFriend(UUID.fromString(friendId));
        return Result.success();
    }

    @Operation(summary = "getFriendsActivePlans", description = "getFriendsActivePlans 接口")
    @GetMapping("/plans")
    public Result<List<FriendPlanVO>> getFriendsActivePlans() {
        List<FriendPlanVO> plans = friendshipService.getFriendsActivePlans();
        return Result.success(plans);
    }

    @Operation(summary = "getFriendRankings", description = "getFriendRankings 接口")
    @GetMapping("/rankings")
    public Result<List<FriendRankingVO>> getFriendRankings() {
        List<FriendRankingVO> rankings = friendshipService.getFriendRankings();
        return Result.success(rankings);
    }
}
