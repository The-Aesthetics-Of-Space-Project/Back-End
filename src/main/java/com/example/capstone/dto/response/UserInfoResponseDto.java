package com.example.capstone.dto;


import com.example.capstone.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {

    private String userId;

    private String profile;

    private String nickname;

    // 유저 팔로워 ( 사용자를 팔로우 하는 사람의 수 )
    private Integer follower;

    // 유저 팔로잉 ( 사용자가 팔로우하는 사람의 수 )
    private Integer followed;

    public static UserInfoResponseDto createDto(User user, int follower, int followed){
        return UserInfoResponseDto.builder()
                .userId(user.getUserId())
                .profile(user.getProfile())
                .nickname(user.getNickname())
                .follower(follower)
                .followed(followed)
                .build();
    }

}
