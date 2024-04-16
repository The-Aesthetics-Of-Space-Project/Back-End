package com.example.capstone.service;


import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserDetailsResponseDto;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.repository.FollowRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.IOException;
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


    @Transactional
    public UserDetailsResponseDto getUserDetails(String email){

        String nickname = userRepository.findByUserId(email).getNickname();

        // 팔로워 몇명인지
        int follower = followRepository.findByNickname_Nickname(nickname).size();

        // 몇명 팔로우 했는지
        int followed = followRepository.findByFollower_Nickname(nickname).size();

        return UserDetailsResponseDto.createDto(userRepository.findByUserId(email),follower, followed);
    }

    @Transactional
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

    @Transactional
    public void setUserDetails(String email, MultipartFile multipartFile)throws IOException {
        File saveFile = new File(email+".jpg");
        multipartFile.transferTo(saveFile);
    }

}
