package com.example.capstone.controller;

import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserPostsResponseDto;
import com.example.capstone.dto.response.contest.UserContestPostsResponseDto;
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




    /**
     * 팔로우
     */
    @Tag(name = "MyPage Controller : 팔로우 관리", description = "MyPage Controller")
    @Operation(summary = "상대방 팔로우 요청", description = "상대방에 대한 팔로우 요청을 보낼때 사용하는 API")
    @GetMapping("/follow")
    public ResponseEntity<String> followUser(@RequestParam String userId, String followId){
        //followUser(팔로우 한사람, 팔로우 피해자)
        mypageService.followUser(userId,followId);

        return ResponseEntity.status(HttpStatus.OK).body("팔로우 하였습니다.");
    }

    /**
     * 팔로우 여부 확인
     */
    @Tag(name = "MyPage Controller : 팔로우 관리", description = "MyPage Controller")
    @Operation(summary = "상대방 팔로우 여부 조회", description = "상대방의 마이페이지 접속 시 상대 팔로우 여부를 조회할때 사용하는 API")
    @GetMapping("/isfollow")
    public boolean getIsFollow(@RequestParam String other,String user){
        //isFollow(팔로우 당한사람:상대방, 팔로우 한사람:나)
        return mypageService.isFollow(other,user);
    }

    /**
     * 팔로워 목록 조회
     */
    @Tag(name = "MyPage Controller : 팔로우 관리", description = "MyPage Controller")
    @Operation(summary = "팔로워 유저 정보 목록 조회", description = "사용자의 팔로워 유저 정보 목록을 조회할 때 사용하는 API")
    @GetMapping("/followers")
    public List<UserFollowResponseDto> getUserFollowers(@RequestParam String userId){
        return mypageService.getUserFollowers(userId);
    }

    /**
     * 팔로잉 목록 조회
     */
    @Tag(name = "MyPage Controller : 팔로우 관리", description = "MyPage Controller")
    @Operation(summary = "팔로잉하고 있는 유저 정보 목록 조회", description = "사용자의 팔로잉 유저 정보 목록을 조회할 때 사용하는 API")
    @GetMapping("/followings")
    public List<UserFollowResponseDto> getUserFollowings(@RequestParam String userId){
        return mypageService.getUserFollowings(userId);
    }

    /**
     * 팔로잉 삭제
     */
    @Tag(name = "MyPage Controller : 팔로우 관리", description = "MyPage Controller")
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
    @Tag(name = "MyPage Controller : 팔로우 관리", description = "MyPage Controller")
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
    @Tag(name = "MyPage Controller : 게시물 목록 관리", description = "MyPage Controller")
    @Operation(summary = "스크랩하고 있는 게시물 목록 조회", description = "사용자의 스크랩 목록을 조회할 때 사용하는 API")
    @GetMapping("/scraps")
    public List<UserPostsResponseDto> getUsersScraps(@RequestParam String userId){
        return mypageService.getUserScraps(userId);
    }

    /**
     * 일반 게시글 좋아요 목록 조회
     */
    @Tag(name = "MyPage Controller : 게시물 목록 관리", description = "MyPage Controller")
    @Operation(summary = "좋아요하고 있는 일반 게시물 목록 조회", description = "사용자의 좋아요 목록을 조회할 때 사용하는 API")
    @GetMapping("/likes/general")
    public List<UserPostsResponseDto> getUserGeneralLikes(@RequestParam String userId){
        return mypageService.getUserGeneralLikes(userId);
    }
    /**
     * 공모전 좋아요 목록 조회
     */
    @Tag(name = "MyPage Controller : 게시물 목록 관리", description = "MyPage Controller")
    @Operation(summary = "좋아요하고 있는 공모전 게시물 목록 조회", description = "사용자의 좋아요 목록을 조회할 때 사용하는 API")
    @GetMapping("/likes/contest")
    public List<UserPostsResponseDto> getUserContestLikes(@RequestParam String userId){
        return mypageService.getUserContestLikes(userId);
    }

    /**
     * 게시물 목록 조회
     */
    @Tag(name = "MyPage Controller : 게시물 목록 관리", description = "MyPage Controller")
    @Operation(summary = "사용자가 작성한 일반 게시물 목록 조회", description = "사용자가 작성한 게시물 목록을 조회할 때 사용하는 API")
    @GetMapping("/posts")
    public List<GeneralPostListResponseDto> getUsersPosts(@RequestParam String userId){
        return mypageService.getUserPosts(userId);
    }

    /**
     * 공모전 게시물 목록 조회
     */
    @Tag(name = "MyPage Controller : 게시물 목록 관리", description = "MyPage Controller")
    @Operation(summary = "사용자가 작성한 공모전 게시물 목록 조회", description = "사용자가 작성한 게시물 목록을 조회할 때 사용하는 API")
    @GetMapping("/contests")
    public List<UserContestPostsResponseDto> getUsersContests(@RequestParam String userId){
        return mypageService.getUserContests(userId);
    }



}
