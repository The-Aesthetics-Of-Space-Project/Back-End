package com.example.capstone.controller;

import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowerResponseDto;
import com.example.capstone.dto.response.UserPostLikesResponseDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.service.GeneralPostService;
import com.example.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class MyPageController {

    @Autowired
    public UserService userService;


    /**
     * 팔로워 목록 조회
     */
    @GetMapping("/followers")
    public UserFollowerResponseDto getUsersFollowers(@RequestParam String userId){
        return userService.getUserFollowers(userId);
    }

    /**
     * 팔로잉 목록 조회
     */
    @GetMapping("/followings")
    public UserFollowerResponseDto getUsersFollowings(@RequestParam String userId){
        return userService.getUserFollowings(userId);
    }

    /**
     * 스크랩 목록 조회
     */
    @GetMapping("/scraps")
    public void getUsersScraps(@RequestParam String userId){

    }

    /**
     * 좋아요 목록 조회
     */
    @GetMapping("/likes")
    public List<UserPostLikesResponseDto> getUsersLikes(@RequestParam String userId){
        return userService.getUserLikes(userId);
    }

    /**
     * 게시물 목록 조회
     */
    @GetMapping("/posts")
    public List<GeneralPostListResponseDto> getUsersPosts(@RequestParam String userId){


        return userService.getUserPosts(userId);
    }

    /**
     * 비밀번호 변경전 확인
     */
    @PostMapping("/check_pass")
    public boolean checkUserPass(@RequestParam String userId, String password){
        return userService.checkPass(userId,password);
    }

    /**
     * 비밀번호 변경
     */
    @PostMapping("/update_pass")
    public ResponseEntity<String> updateUserPass(@RequestParam String userId, String password){
        userService.updateUserPass(userId,password);
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경이 완료되었습니다.");
    }


}
