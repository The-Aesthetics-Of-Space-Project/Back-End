package com.example.capstone.service;


import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
import com.example.capstone.dto.response.*;
import com.example.capstone.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


import com.example.capstone.dto.UserSignupDto;

import com.example.capstone.entity.user.User;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;


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

    @Autowired
    private ScrapRepository scrapRepository;


    /**
     * 유저 정보 조회
     */
    @Transactional
    public UserDetailsResponseDto getUserDetails(String userId) {

        String nickname = userRepository
                .findByUserId(userId)
                .orElseThrow()
                .getNickname();

        // 팔로워 몇명인지
        Long follower = followRepository.countByUserId_UserId(userId);

        // 몇명 팔로우 했는지
        Long followed = followRepository.countByFollower_UserId(userId);

        Long likes = generalLikeRepository.countByUser_UserId(userId);

        Long scraps = scrapRepository.countByUser_UserId(userId);

        return UserDetailsResponseDto.createDto(
                userRepository.findByUserId(userId).orElseThrow(),
                follower,
                followed,
                likes,
                scraps);
    }




    /**
     * 회원 정보 수정
     */

    public UserUpDetailsResponseDto userDetail(String userId){

        return UserUpDetailsResponseDto
                .createDto(userRepository.findByUserId(userId).get());
    }
    @Transactional
    public void updateUserDetails(String email, UserDetailsUpdateRequestDto userDetailsUpdateRequestDto) throws IOException {

        //
        userRepository.findByUserId(email)
                .orElseThrow()
                .updateDetails(email,userDetailsUpdateRequestDto);

        // 파일이 비어있지 않으면 profile/image 경로에 이미지 저장
        if( userDetailsUpdateRequestDto.getProfile()!=null ) {
            File saveFile = new File(email + ".jpg");
            userDetailsUpdateRequestDto
                    .getProfile()
                    .transferTo(saveFile);
        }
    }




    /**
     * 닉네임 중복 확인
     */
    @Transactional
    public boolean checkNickname(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }

    /**
     * 비밀번호 변경전 확인
     */
    @Transactional
    public boolean checkPass(String userId, String password) {
        return userRepository.findByUserId(userId)
                .get()
                .getPassword().equals(password);
    }

    /**
     * 비밀번호 변경
     */
    @Transactional
    public void updateUserPass(String userId, String password) {
        userRepository.findByUserId(userId)
                .get()
                .updatePassword(password);
    }


    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteUserDetails(String email) {
        userRepository.deleteById(email);
    }



    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유효성 검사 valid ( id, pw, nickname )
    @Transactional
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    // 회원 계정 생성(중복되는 id 찾기)
    public User create(UserSignupDto userDto) throws IOException{
        String id = userDto.getUserId();
        log.info("DTO의 아이디" + id);

        Optional<User> target = userRepository.findById(id);
        log.info("Target" + target);

        target.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        });


        //1. Dto -> Entity
        User user = userDto.toEntity();

        //2. repository에게 entity를 디비에 저장하게 시킴
        User saved = userRepository.save(user);

        return saved;

    }

    // 아이디 중복확인
    public boolean existsById(String id) {

        return userRepository.existsById(id);
    }

    // 닉네임 중복확인
    public boolean existsByNickName(String nickname) {

        return userRepository.existsByNickname(nickname);
    }

}

