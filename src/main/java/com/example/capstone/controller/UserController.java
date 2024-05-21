package com.example.capstone.controller;

import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
import com.example.capstone.dto.response.UserDetailsResponseDto;
import com.example.capstone.service.UserService;
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


        /*
    유저 리스트를 불러올 일이 없어서 폐기
     */
//    @GetMapping
//    public List<User> getUsersInfo(){
//        return userService.getUsersInfo()
//                .stream()
//                .
//    }

    /**
     * 회원 정보 조회
     */
    @GetMapping("/details")
    public UserDetailsResponseDto getUserDetails(@RequestParam String userId) throws IOException{
        UserDetailsResponseDto userDetailsResponseDto = userService.getUserDetails(userId);
        return userDetailsResponseDto;
    }




    /**
     * 회원 정보 수정
     */
    @PostMapping("/update")
    public ResponseEntity<String> updateUserDetails(@RequestParam String u_id, UserDetailsUpdateRequestDto userDetailsUpdateRequestDto)throws IOException {
        log.info(u_id);
        userService.updateUserDetails(u_id, userDetailsUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 등록에 성공하였습니다.");
    }

    /**
     * 회원 프로필 사진 조회
     */
    @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserImage(@RequestParam String userId)throws IOException{
        InputStream is = new FileInputStream("C:/profile/image/"+userId+".jpg");
        log.info(is.toString());
        return IOUtils.toByteArray(is);
    }

    /**
     * 회원 정보 수정 닉네임 체크
     */
    @GetMapping("/check_nick")
    public boolean checkNickname(@RequestParam String nickname){
        return userService.checkNickname(nickname);
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserDetails(@RequestParam String u_id){
        userService.deleteUserDetails(u_id);
        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴 완료");
    }
}
