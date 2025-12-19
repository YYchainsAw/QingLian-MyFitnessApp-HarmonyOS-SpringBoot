package com.yychainsaw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yychainsaw.pojo.dto.PostCreateDTO;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.vo.PostVO;
import com.yychainsaw.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/community")
public class PostController {

    @Autowired
    private PostService postService;

    // 操作 2: 发布帖子 (包含防刷帖和VIP赞逻辑)
    @PostMapping("/posts")
    public Result createPost(@RequestAttribute("id") String userIdStr,
                             @RequestBody @Validated PostCreateDTO dto) {
        postService.createPost(UUID.fromString(userIdStr), dto);
        return Result.success();
    }

    // 操作 4: 分页获取 Feed 流
    @GetMapping("/feed")
    public Result<Page<PostVO>> getFeed(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return Result.success(postService.getPostFeed(page, size));
    }

    // 操作 6: 点赞
    @PostMapping("/posts/{postId}/like")
    public Result likePost(@PathVariable Long postId) {
        postService.likePost(postId);
        return Result.success();
    }

    // 操作 8: 删除帖子
    @DeleteMapping("/posts/{postId}")
    public Result deletePost(@RequestAttribute("id") String userIdStr,
                             @PathVariable Long postId) {
        postService.deletePost(UUID.fromString(userIdStr), postId);
        return Result.success();
    }

    // 操作 11: 活跃达人榜
    @GetMapping("/influencers")
    public Result<List<Map<String, Object>>> getInfluencers() {
        return Result.success(postService.getInfluencers());
    }

    // 操作 14: 潜在好友推荐
    @GetMapping("/recommend-friends")
    public Result<List<Map<String, Object>>> getPotentialFriends(@RequestAttribute("id") String userIdStr) {
        return Result.success(postService.getPotentialFriends(UUID.fromString(userIdStr)));
    }

    // 操作 15: 数据统计 (后台用)
    @GetMapping("/stats/gender-weight")
    public Result<List<Map<String, Object>>> getStats() {
        return Result.success(postService.getStats());
    }
}
