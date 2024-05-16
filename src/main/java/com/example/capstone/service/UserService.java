package com.example.capstone.service;


import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserDetailsResponseDto;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.repository.FollowRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




    @Transactional
    public UserDetailsResponseDto getUserDetails(String email){

        String nickname = userRepository.findByUserId(email).getNickname();

        // 팔로워 몇명인지
        int follower = followRepository.findByUserId_UserId(email).size();

        // 몇명 팔로우 했는지
        int followed = followRepository.findByFollower_UserId(email).size();

        return UserDetailsResponseDto.createDto(
                userRepository.findByUserId(email),
                follower,
                followed,
                0); // 임시 좋아요
    }


    /**
     * 팔로워 목록 조회
     */
    @Transactional
    public List<UserFollowResponseDto> getUserFollowers(String email){

        List<UserFollowResponseDto> userFollowers = new ArrayList<>();

        String nickname = userRepository.findByUserId(email).getNickname();

        for(Follow follow : followRepository.findByUserId_UserId(email)){
            userFollowers.add(UserFollowResponseDto.createDto(follow));
        }


        return userFollowers;
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public void updateUserDetails(String email, UserDetailsUpdateRequestDto userDetailsUpdateRequestDto)throws IOException {

        //
        userRepository.findByUserId(email)
                .updateDetails(userDetailsUpdateRequestDto);

        // 파일 명 : 이메일 + .jpg
        File saveFile = new File(email+".jpg");

        // 이미지 저장
        userDetailsUpdateRequestDto
                .getProfile()
                .transferTo(saveFile);

    }


    /**
     * 닉네임 중복 확인
     */

    @Transactional
    public boolean checkNickname(String nickname){
        return userRepository.findByNickname(nickname).isEmpty();
    }




    // 삭제가 안됨
    @Transactional
    public void deleteUserDetails(String email){
        userRepository.deleteById(email);

    }


}
