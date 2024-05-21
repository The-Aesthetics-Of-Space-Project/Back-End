package com.example.capstone.service;


import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
import com.example.capstone.dto.response.*;
import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.repository.FollowRepository;
import com.example.capstone.repository.GeneralLikeRepository;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private GeneralPostRepository generalPostRepository;

    @Autowired
    private GeneralLikeRepository generalLikeRepository;



    /**
     * 유저 정보 조회
     */
    @Transactional
    public UserDetailsResponseDto getUserDetails(String userId){

        String nickname = userRepository
                .findByUserId(userId)
                .orElseThrow()
                .getNickname();

        // 팔로워 몇명인지
        Long follower = followRepository.countByUserId_UserId(userId);

        // 몇명 팔로우 했는지
        Long followed = followRepository.countByFollower_UserId(userId);

        Long likes = generalLikeRepository.countByUser_UserId(userId);

        return UserDetailsResponseDto.createDto(
                userRepository.findByUserId(userId).orElseThrow(),
                follower,
                followed,
                likes);
    }


    /**
     * 팔로워 목록 조회
     */
    @Transactional
    public UserFollowerResponseDto getUserFollowers(String userId){


        List<String> followerList = new ArrayList<>();

        for(Follow follower : followRepository.findByUserId_UserId(userId)){
            followerList.add(follower.getFollower().getNickname());
        }

        return new UserFollowerResponseDto(followerList);
    }


    /**
     * 팔로워 목록 조회
     */
    @Transactional
    public UserFollowerResponseDto getUserFollowings(String userId){


        List<String> followingList = new ArrayList<>();

        for(Follow follower : followRepository.findByFollower_UserId(userId)){
            followingList.add(follower.getFollower().getNickname());
        }

        return new UserFollowerResponseDto(followingList);
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public void updateUserDetails(String email, UserDetailsUpdateRequestDto userDetailsUpdateRequestDto)throws IOException {

        //
        userRepository.findByUserId(email)
                .orElseThrow()
                .updateDetails(userDetailsUpdateRequestDto);

        // 파일 명 : 이메일 + .jpg
        File saveFile = new File(email+".jpg");

        // 이미지 저장
        userDetailsUpdateRequestDto
                .getProfile()
                .transferTo(saveFile);

    }

    /**
     * 회원 게시물 조회
     */

    @Transactional
    public List<GeneralPostListResponseDto> getUserPosts(String userId){
        return generalPostRepository.findByUser_UserId(userId)
                .stream()
                .map(GeneralPostListResponseDto::createDto)
                .toList();
    }


    /**
     * 닉네임 중복 확인
     */
    @Transactional
    public boolean checkNickname(String nickname){
        return userRepository.findByNickname(nickname).isEmpty();
    }

    /**
     * 비밀번호 변경전 확인
     */
    @Transactional
    public boolean checkPass(String userId,String password){
        return userRepository.findByUserId(userId)
                .get()
                .getPassword().equals(password);
    }

    /**
     * 비밀번호 변경
     */
    @Transactional
    public void updateUserPass(String userId, String password){
        userRepository.findByUserId(userId)
                .get()
                .updatePassword(password);
    }


    /**
     *  회원 탈퇴
     */
    @Transactional
    public void deleteUserDetails(String email){
        userRepository.deleteById(email);
    }


    /**
     * 회원 좋아요 목록 조회
     */
    @Transactional
    public List<UserPostLikesResponseDto> getUserLikes(String userId){
        return generalLikeRepository.findByUser_UserId(userId)
                .stream()
                .map(UserPostLikesResponseDto::createDto)
                .toList();
    }

}
