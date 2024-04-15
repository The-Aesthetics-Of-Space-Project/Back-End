package com.example.capstone.controller;

import com.example.capstone.dto.UserFollowResponseDto;
import com.example.capstone.dto.UserInfoResponseDto;
import com.example.capstone.entity.User;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;


        /*
    유저 리스트를 불러올 일이 없어서 폐기
     */
//    @GetMapping
//    public List<User> getUsersInfo(){
//        return userService.getUsersInfo()
//                .stream()
//                .
//    }

    @GetMapping("/{email}")
    public UserInfoResponseDto getUserInfo(@PathVariable String email){
        return userService.getUserInfo(email);
    }

    @GetMapping("/{email}/follow")
    public List<UserFollowResponseDto> getUserFollow(@PathVariable String email){
        return userService.getUserFollowers(email);
    }


}
