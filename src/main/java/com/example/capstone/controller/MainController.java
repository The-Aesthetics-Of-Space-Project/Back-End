package com.example.capstone.controller;

import com.example.capstone.dto.UserLoginDto;
import com.example.capstone.dto.UserSignupDto;
import com.example.capstone.repository.UserRepository;
import com.example.capstone.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    //로그인 페이지에서 아이디 및 비밀번호 검사
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserLoginDto userLoginDto, Model model, HttpSession session) {

        String id = userRepository.findID(userLoginDto.getUserId());
        model.addAttribute("warningMessage", "");
        model.addAttribute("errorMessage", "");
        Map<String, Object> responseData = new HashMap<>();
        log.info("userid: " +  userLoginDto.getUserId(),"userpasword" + userLoginDto.getPassword());
        if (id == null) {
            //아이디 없을 때 로직 처리
            model.addAttribute("warningMessage", "해당 사용자명으로 등록된 아이디가 없습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이이디가 다릅니다.");
        } else {
            //있을 때 로직 처리
            if (!userLoginDto.getPassword().equals(userRepository.findPW(userLoginDto.getUserId()))) {
                model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
                log.info("비밀번호에러");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀 번호가 다릅니다.");
            } else
            {
                responseData.put("message", "success");
                // 필요한 경우 사용자 정보 등 추가 데이터 포함
                responseData.put("userId", userLoginDto.getUserId());
                session.setAttribute("ID",userLoginDto.getUserId());
                return ResponseEntity.ok().body(responseData);
            }
        }
    }


    // 유효성 검사 로직 Errors
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> execSignup(@Valid @RequestBody UserSignupDto userDto, Errors errors) {
        Map<String, Object> response = new HashMap<>();

        if (errors.hasErrors()) {
            // 유효성 검사 실패 시
            Map<String, String> validatorResult = userService.validateHandling(errors);
            response.put("success", false);
            response.put("errors", validatorResult);
            return ResponseEntity.badRequest().body(response);
        } else {
            // 유효성 검사 성공 시
            userService.create(userDto);
            response.put("success", true);
            response.put("message", "회원가입에 성공하였습니다!");
            return ResponseEntity.ok(response);
        }
    }

}
