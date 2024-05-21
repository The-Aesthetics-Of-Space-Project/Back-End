package com.example.capstone.dto;

import com.example.capstone.entity.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    //빌더 패턴
    @Builder
    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .nickname(nickname)
                .profile("http://119.198.33.129:8080/users/image?userId=default")
                .build();
    }
}
