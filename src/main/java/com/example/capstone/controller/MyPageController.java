package com.example.capstone.controller;

import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserPostLikesResponseDto;
import com.example.capstone.service.MypageService;
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

    @Autowired
    public MypageService mypageService;


    /**
     * 팔로워 목록 조회
     */
    @GetMapping("/followers")
    public List<UserFollowResponseDto> getUserFollowers(@RequestParam String userId){
        return mypageService.getUserFollowers(userId);
    }

    /**
     * 팔로잉 목록 조회
     */
    @GetMapping("/followings")
    public List<UserFollowResponseDto> getUserFollowings(@RequestParam String userId){
        return mypageService.getUserFollowings(userId);
    }

    /**
     * 팔로잉 삭제
     */
    @DeleteMapping("/delete_follow")
    public ResponseEntity<String> deleteUserFollowing(@RequestParam String userId,@RequestParam String follow){
        mypageService.deleteUserFollower(userId,follow);
        return ResponseEntity.status(HttpStatus.OK).body("언팔하였습니다.");
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
        return mypageService.getUserLikes(userId);
    }

    /**
     * 게시물 목록 조회
     */
    @GetMapping("/posts")
    public List<GeneralPostListResponseDto> getUsersPosts(@RequestParam String userId){


        return mypageService.getUserPosts(userId);
    }

    /**
     * 비밀번호 변경전 확인
     */
    @PutMapping("/check_pass")
    public boolean checkUserPass(@RequestParam String userId, String password){
        return userService.checkPass(userId,password);
    }

    /**
     * 비밀번호 변경
     */
    @PutMapping("/update_pass")
    public ResponseEntity<String> updateUserPass(@RequestParam String userId, String password){
        userService.updateUserPass(userId,password);
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경이 완료되었습니다.");
    }


}
