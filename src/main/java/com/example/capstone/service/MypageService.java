package com.example.capstone.service;


import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserGeneralPostResponseDto;
import com.example.capstone.entity.follow.FollowId;
import com.example.capstone.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private GeneralLikeRepository generalLikeRepository;

    @Autowired
    private GeneralPostRepository generalPostRepository;

    @Autowired
    private ScrapRepository scrapRepository;


    /**
     * 팔로워 목록 조회
     */
    @Transactional
    public List<UserFollowResponseDto> getUserFollowers(String userId) {



        return followRepository.findByUserId_UserId(userId)
                .stream()
                .map(UserFollowResponseDto::createFollwerDto)
                .toList();


    }


    /**
     * 팔로잉 목록 조회
     */
    @Transactional
    public List<UserFollowResponseDto> getUserFollowings(String userId) {

        return followRepository.findByFollower_UserId(userId)
                .stream()
                .map(UserFollowResponseDto::createFollwingDto)
                .toList();
    }


    /**
     * 회원 게시물 조회
     */

    @Transactional
    public List<GeneralPostListResponseDto> getUserPosts(String userId) {
        return generalPostRepository.findByUser_UserId(userId)
                .stream()
                .map(GeneralPostListResponseDto::createDto)
                .toList();
    }

    /**
     * 스크랩 목록 조회
     */
    @Transactional
    public List<UserGeneralPostResponseDto> getUserScraps(String userId){
        return scrapRepository.findByUser_UserId(userId)
                .stream()
                .map(UserGeneralPostResponseDto::createScrapsDto)
                .toList();
    }


    /**
     * 회원 좋아요 목록 조회
     */

    public List<UserGeneralPostResponseDto> getUserLikes(String userId) {
        return generalLikeRepository.findByUser_UserId(userId)
                .stream()
                .map(UserGeneralPostResponseDto::createLikesDto)
                .toList();
    }

    /**
     * 언팔로우
     */
    public void deleteUserFollowing(String userId, String follow){
        //followId(userID-팔로우 당한사람, follower-팔로우한사람)
        FollowId followId = new FollowId(follow,userId);
        followRepository.deleteById(followId);
    }

    /**
     * 팔로워 삭제
     */

    public void deleteUserFollower(String userId,String follower){
        //followId(userID-팔로우 당한사람, follower-팔로우한사람)
        FollowId followId = new FollowId(userId,follower);
        followRepository.deleteById(followId);
    }


}
