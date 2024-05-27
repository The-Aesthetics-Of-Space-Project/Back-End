package com.example.capstone.service;


import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserPostsResponseDto;
import com.example.capstone.dto.response.contest.UserContestPostsResponseDto;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.entity.follow.FollowId;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ContestPostRepository contestPostRepository;

    @Autowired
    private ContestLikeRepository contestLikeRepository;



    @Transactional
    public void followUser(String userId, String followId){


        //팔로우 한사람
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        //팔로우 당한사람
        User followed = userRepository.findByUserId(followId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));


        //팔로우 생성중
        //new FollowId(팔로우 당한사람, 팔로우 한사람)
        Follow follow = Follow.builder()
                .id(new FollowId(followId,userId))
                .userId(followed)
                .follower(user)
                .build();

        followRepository.save(follow);
    }


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
     * 회원 게시물 조회
     */

    @Transactional
    public List<UserContestPostsResponseDto> getUserContests(String userId) {
        return contestPostRepository.findByUser_UserId(userId)
                .stream()
                .map(UserContestPostsResponseDto::createDto)
                .toList();
    }

    /**
     * 스크랩 목록 조회
     */
    @Transactional
    public List<UserPostsResponseDto> getUserScraps(String userId){
        return scrapRepository.findByUser_UserId(userId)
                .stream()
                .map(UserPostsResponseDto::createScrapsDto)
                .toList();
    }


    /**
     * 회원 좋아요 목록 조회
     */

    public List<UserPostsResponseDto> getUserLikes(String userId) {

        List<UserPostsResponseDto> dtoList = new ArrayList<>();

        dtoList.addAll(generalLikeRepository.findByUser_UserId(userId)
                .stream()
                .map(UserPostsResponseDto::createGeneralLikesDto)
                .toList());

        dtoList.addAll(contestLikeRepository.findByUser_UserId(userId)
                .stream()
                .map(UserPostsResponseDto::createContestLikesDto)
                .toList());

        return dtoList;
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
