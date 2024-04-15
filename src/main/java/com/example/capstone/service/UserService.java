package com.example.capstone.service;


import com.example.capstone.dto.UserFollowResponseDto;
import com.example.capstone.dto.UserInfoResponseDto;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.repository.FollowRepository;
import com.example.capstone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    /*
    유저 리스트를 불러올 일이 없어서 폐기
     */
//    public List<UserInfoResponseDto> getUsersInfo(){
////        log.info(userRepository.findAll().toString());
//        return userRepository.findAll();
//    }


    public UserInfoResponseDto getUserInfo(String email){

        String nickname = userRepository.findByUserId(email).getNickname();

        // 팔로워 몇명인지
        int follower = followRepository.findByNickname_Nickname(nickname).size();

        // 몇명 팔로우 했는지
        int followed = followRepository.findByFollower_Nickname(nickname).size();

        return UserInfoResponseDto.createDto(userRepository.findByUserId(email),follower, followed);
    }

    public List<UserFollowResponseDto> getUserFollowers(String email){

        List<UserFollowResponseDto> userFollowers = new ArrayList<>();

        String nickname = userRepository.findByUserId(email).getNickname();

        /*
        유저의 팔로워 정보를 받아온 다음      ( List<Follow> 타입 )
        향상for문을 활용해 각 follow 객체들을
         */
        for(Follow follow : followRepository.findByNickname_Nickname(nickname)){
            userFollowers.add(UserFollowResponseDto.createDto(follow));
        }


        return userFollowers;
    }

}
