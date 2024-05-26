package com.example.capstone.controller;

import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserGeneralPostResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.service.MypageService;
import com.example.capstone.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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





    @GetMapping("/follow")
    public ResponseEntity<String> followUser(@RequestParam String userId, String followId){
        //followUser(팔로우 한사람, 팔로우 피해자)
        mypageService.followUser(userId,followId);

        return ResponseEntity.status(HttpStatus.OK).body("팔로우 하였습니다.");
    }
    /**
     * 팔로워 목록 조회
     */
    @Tag(name = "MyPage Controller : 팔로워 목록 조회", description = "MyPage Controller")
    @Operation(summary = "팔로워 유저 정보 목록 조회", description = "사용자의 팔로워 유저 정보 목록을 조회할 때 사용하는 API")
    @GetMapping("/followers")
    public List<UserFollowResponseDto> getUserFollowers(@RequestParam String userId){
        return mypageService.getUserFollowers(userId);
    }

    /**
     * 팔로잉 목록 조회
     */
    @Tag(name = "MyPage Controller : 팔로잉 목록 조회", description = "MyPage Controller")
    @Operation(summary = "팔로잉하고 있는 유저 정보 목록 조회", description = "사용자의 팔로잉 유저 정보 목록을 조회할 때 사용하는 API")
    @GetMapping("/followings")
    public List<UserFollowResponseDto> getUserFollowings(@RequestParam String userId){
        return mypageService.getUserFollowings(userId);
    }

    /**
     * 팔로잉 삭제
     */
    @Tag(name = "MyPage Controller : 팔로잉 취소", description = "MyPage Controller")
    @Operation(summary = "팔로잉 취소", description = "팔로잉을 취소할 때 사용하는 API")
    @DeleteMapping("/unfollowing")
    public ResponseEntity<String> deleteUserFollowing(@RequestParam String userId,@RequestParam String follow){
        log.info("언팔로우 = userId: "+userId+"follow: "+follow);
        mypageService.deleteUserFollowing(userId,follow);
        return ResponseEntity.status(HttpStatus.OK).body("언팔하였습니다.");
    }

    /**
     * 팔로워 삭제
     */
    @Tag(name = "MyPage Controller : 팔로워 삭제", description = "MyPage Controller")
    @Operation(summary = "팔로워 삭제", description = "팔로워를 삭제할 때 사용하는 API")
    @DeleteMapping("/unfollower")
    public ResponseEntity<String> deleteUserFollower(@RequestParam String userId, @RequestParam String follower){
        log.info("언팔로우 = userId: "+userId+"follower: "+follower);
        mypageService.deleteUserFollower(userId,follower);
        return ResponseEntity.status(HttpStatus.OK).body("팔로워를 삭제하였습니다.");
    }



    /**
     * 스크랩 목록 조회
     */
    @GetMapping("/scraps")
    public List<UserGeneralPostResponseDto> getUsersScraps(@RequestParam String userId){
        return mypageService.getUserScraps(userId);
    }

    /**
     * 좋아요 목록 조회
     */
    @GetMapping("/likes")
    public List<UserGeneralPostResponseDto> getUsersLikes(@RequestParam String userId){
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
     * 공모전 게시물 목록 조회
     */
    @GetMapping("/contests")
    public List<ContestPostsResponseDto> getUsersContests(@RequestParam String userId){
        return mypageService.getUserContests(userId);
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
