package com.yychainsaw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yychainsaw.pojo.dto.PostCreateDTO;
import com.yychainsaw.pojo.dto.PostUpdateDTO;
import com.yychainsaw.pojo.vo.GenderStatVO;
import com.yychainsaw.pojo.vo.InfluencerVO;
import com.yychainsaw.pojo.vo.PostVO;
import com.yychainsaw.pojo.vo.PotentialFriendVO;

import java.util.List;

public interface PostService {
    void createPost(PostCreateDTO dto);

    Page<PostVO> getPostFeed(int page, int size);

    void likePost(Long postId);

    void deletePost(Long postId);

    void updatePost(Long postId, PostUpdateDTO dto);

    List<InfluencerVO> getInfluencers();

    List<PotentialFriendVO> getPotentialFriends();

    List<GenderStatVO> getStats();
}
