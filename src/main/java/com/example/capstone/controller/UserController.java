package com.example.capstone.controller;

import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
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
    public UserDetailsResponseDto getUserDetails(@PathVariable String email) throws IOException{
        UserDetailsResponseDto userDetailsResponseDto = userService.getUserDetails(email);
        return userDetailsResponseDto;
    }




    // 임시 회원정보수정
    @PostMapping("/update")
    public ResponseEntity<String> updateUserDetails(@RequestParam String u_id, UserDetailsUpdateRequestDto userDetailsUpdateRequestDto)throws IOException {
        log.info(u_id);
        userService.updateUserDetails(u_id, userDetailsUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 등록에 성공하였습니다.");
    }

    @GetMapping(value = "/{email}/image",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserImage(@PathVariable String email)throws IOException{
        InputStream is = new FileInputStream("C:/profile/image/"+email+".jpg");
        log.info(is.toString());
        return IOUtils.toByteArray(is);
    }

    @GetMapping("/check_nick")
    public ResponseEntity<String> checkNickname(@RequestParam String nickname){
        if(userService.checkNickname(nickname))
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 닉네임");
        else
            return ResponseEntity.status(HttpStatus.OK).body("사용중인 닉네임");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserDetails(@RequestParam String u_id){
        userService.deleteUserDetails(u_id);
        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴 완료");
    }






}
