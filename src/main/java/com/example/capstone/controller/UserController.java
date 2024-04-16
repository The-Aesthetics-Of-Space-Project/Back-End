package com.example.capstone.controller;

import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserDetailsResponseDto;
import com.example.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    public UserDetailsResponseDto getUserDetails(@PathVariable String email){
        return userService.getUserDetails(email);
    }

    @GetMapping("/{email}/follow")
    public List<UserFollowResponseDto> getUserFollow(@PathVariable String email){
        return userService.getUserFollowers(email);
    }

    // 임시 회원정보수정
    @PostMapping("/{email}")
    public ResponseEntity<String> setUserDetails(@PathVariable String email, MultipartFile multipartFile)throws IOException {
        log.info(multipartFile.getContentType());
        userService.setUserDetails(email,multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 등록에 성공하였습니다.");
    }

    @GetMapping(value = "/{email}/image",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserImage(@PathVariable String email)throws IOException{
        InputStream is = new FileInputStream("C:/profile/image/"+email+".jpg");

        log.info(is.toString());
        return IOUtils.toByteArray(is);
    }



}
