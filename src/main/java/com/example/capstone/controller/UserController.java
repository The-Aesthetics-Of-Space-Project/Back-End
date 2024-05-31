package com.example.capstone.controller;

import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
import com.example.capstone.dto.response.UserDetailsResponseDto;
import com.example.capstone.dto.response.UserUpDetailsResponseDto;
import com.example.capstone.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;

    /**
     * 회원 정보 조회
     */
    @Tag(name = "User Controller : 회원 정보 관리", description = "User Controller")
    @Operation(summary = "사용자의 회원 정보를 조회", description = "사용자의 회원 정보를 조회할 때 사용하는 API")
    @GetMapping("/details")
    public UserDetailsResponseDto getUserDetails(@RequestParam String nick) throws IOException{
        UserDetailsResponseDto userDetailsResponseDto = userService.getUserDetails(nick);
        return userDetailsResponseDto;
    }

    /**
     * 회원 정보 수정
     */
    @Tag(name = "User Controller : 회원 정보 관리", description = "User Controller")
    @Operation(summary = "사용자의 회원 정보를 수정", description = "사용자의 회원 정보를 수정할 때 사용하는 API")
    @PutMapping("/update")
    public ResponseEntity<String> updateUserDetails(@RequestParam String userId, UserDetailsUpdateRequestDto userDetailsUpdateRequestDto)throws IOException {
        log.info(userDetailsUpdateRequestDto.toString());
        userService.updateUserDetails(userId, userDetailsUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 등록에 성공하였습니다.");
    }

    /**
     * 회원 프로필 사진 조회
     */
    @Tag(name = "User Controller : 회원 정보 관리", description = "User Controller")
    @Operation(summary = "사용자의 프로필 사진을 조회", description = "사용자의 프로필 사진을 조회할 때 사용하는 API")
    @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserImage(@RequestParam String userId)throws IOException{
        InputStream is = new FileInputStream("C:/temp/profile/"+userId+".jpg");
        log.info("이미지 조회"+userId);
        byte[] image = IOUtils.toByteArray(is);
        is.close();
        return image;
    }

    /**
     * 회원 탈퇴
     */
    @Tag(name = "User Controller : 회원 정보 관리", description = "User Controller")
    @Operation(summary = "회원 정보 탈퇴", description = "사용자가 회원 정보를 삭제하고 탈퇴할 때 사용하는 API")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserDetails(@RequestParam String userId){
        userService.deleteUserDetails(userId);
        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴 완료");
    }
}
