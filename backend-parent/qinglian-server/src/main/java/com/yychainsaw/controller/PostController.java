package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yychainsaw.dto.PageBean;
import com.yychainsaw.dto.PostCreateDTO;
import com.yychainsaw.dto.Result;
import com.yychainsaw.vo.InfluencerVO;
import com.yychainsaw.vo.PostVO;
import com.yychainsaw.vo.PotentialFriendVO;
import com.yychainsaw.service.PostService;
import com.yychainsaw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Post管理", description = "Post相关的API接口")
@RestController
@RequestMapping("/community")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;


    @Operation(summary = "createPost", description = "createPost 接口")
    @PostMapping("/posts")
    public Result createPost(@RequestBody @Validated PostCreateDTO dto) {
        postService.createPost(dto);
        return Result.success();
    }


    @Operation(summary = "getFeed", description = "getFeed 接口")
    @GetMapping("/feed")
    public Result<PageBean<PostVO>> getFeed(Integer page,
                                            Integer size) {
        return Result.success(postService.getPostFeed(page, size));
    }


    @Operation(summary = "likePost", description = "likePost 接口")
    @PostMapping("/posts/{postId}/like")
    public Result likePost(@PathVariable Long postId) {
        postService.likePost(postId);
        return Result.success();
    }


    @Operation(summary = "deletePost", description = "deletePost 接口")
    @DeleteMapping("/posts/{postId}")
    public Result deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return Result.success();
    }

    @Operation(summary = "getInfluencers", description = "getInfluencers 接口")
    @GetMapping("/influencers")
    public Result<List<InfluencerVO>> getInfluencers() {
        return Result.success(postService.getInfluencers());
    }

    // 操作 14: 潜在好友推荐
    @Operation(summary = "getPotentialFriends", description = "getPotentialFriends 接口")
    @GetMapping("/recommend-friends")
    public Result<List<PotentialFriendVO>> getPotentialFriends() {
        return Result.success(postService.getPotentialFriends());
    }

    @Operation(summary = "接口", description = "接口 接口")
    @GetMapping("/stats/gender-weight")
    public Result<List<Map<String, Object>>> getStats() {
        List<Map<String, Object>> stats = userService.getGenderWeightStats();
        return Result.success(stats);
    }
}
