package com.example.capstone.service;

import com.example.capstone.dto.UserLoginDto;
import com.example.capstone.dto.UserSignupDto;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@NoArgsConstructor
public class UserService {
    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유효성 검사 valid ( id, pw, nickname )
    @Transactional
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    // 회원 계정 생성(중복되는 id 찾기)
    public User create(UserSignupDto userDto) {
        String id = userDto.getUserId();
        log.info("DTO의 아이디" + id);

        Optional<User> target = userRepository.findById(id);
        log.info("Target" + target);

        target.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        });

        //1. Dto -> Entity
        User user = userDto.toEntity();

        //2. repository에게 entity를 디비에 저장하게 시킴
        User saved = userRepository.save(user);

        return saved;

    }

    // 아이디 중복확인
    public boolean existsById(String id) {

        return userRepository.existsById(id);
    }

    // 닉네임 중복확인
    public boolean existsByNickName(String nickname){

        return userRepository.existsByNickname(nickname);
    }
}
