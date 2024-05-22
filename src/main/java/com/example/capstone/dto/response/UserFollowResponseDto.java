package com.example.capstone.dto.response;

import com.example.capstone.entity.follow.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserFollowResponseDto {
    private String userId;
    private String nickname;
    private String profile;

    public static UserFollowResponseDto createFollwerDto(Follow follow) {
        return UserFollowResponseDto.builder()
                .nickname(follow.getFollower().getNickname())
                .userId(follow.getFollower().getUserId())
                .profile(follow.getFollower().getProfile())
                .build();
    }
    public static UserFollowResponseDto createFollwingDto(Follow follow) {
        return UserFollowResponseDto.builder()
                .nickname(follow.getUserId().getNickname())
                .userId(follow.getUserId().getUserId())
                .profile(follow.getUserId().getProfile())
                .build();
    }

}
