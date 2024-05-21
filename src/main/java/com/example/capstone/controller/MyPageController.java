package com.example.capstone.controller;

import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowerResponseDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.service.GeneralPostService;
import com.example.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 스크랩 목록 조회
     */
    @GetMapping("/scraps")
    public void getUsersScraps(@RequestParam String userId){

    }

    /**
     * 좋아요 목록 조회
     */
    @GetMapping("/likes")
    public void getUsersLikes(@RequestParam String userId){

    }

    /**
     * 게시물 목록 조회
     */
    @GetMapping("/posts")
    public List<GeneralPostListResponseDto> getUsersPosts(@RequestParam String userId){


        return userService.getUserPosts(userId);
    }



}
