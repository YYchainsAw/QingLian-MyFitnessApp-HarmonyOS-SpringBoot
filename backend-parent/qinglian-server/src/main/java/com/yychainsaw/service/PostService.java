package com.yychainsaw.service;

import com.yychainsaw.dto.PageBean;
import com.yychainsaw.dto.PostCreateDTO;
import com.yychainsaw.dto.PostUpdateDTO;
import com.yychainsaw.vo.GenderStatVO;
import com.yychainsaw.vo.InfluencerVO;
import com.yychainsaw.vo.PostVO;
import com.yychainsaw.vo.PotentialFriendVO;

import java.util.List;

public interface PostService {
    void createPost(PostCreateDTO dto);

    PageBean<PostVO> getPostFeed(Integer page, Integer size);

    void likePost(Long postId);

    void deletePost(Long postId);

    void updatePost(Long postId, PostUpdateDTO dto);

    List<InfluencerVO> getInfluencers();

    List<PotentialFriendVO> getPotentialFriends();

    List<GenderStatVO> getStats();
}
