package com.example.capstone.dto.response;

import com.example.capstone.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpDetailsResponseDto {
    private String userId;
    private String nickname;
    private String profile;

    public static UserUpDetailsResponseDto createDto(User user){
        return UserUpDetailsResponseDto.builder()
                .nickname(user.getNickname())
                .userId(user.getUserId())
                .profile(user.getProfile())
                .build();
    }
}
