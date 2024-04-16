package com.example.capstone.dto.response;


import com.example.capstone.entity.follow.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowResponseDto {

    private String userId;

    private String follower;

    public static UserFollowResponseDto createDto(Follow follow){
        return UserFollowResponseDto.builder()
                .userId(follow.getNickname().getNickname())
                .follower(follow.getFollower().getNickname())
                .build();
    }
//    public static UserFollowResponseDto createDto(Follow follow){
//        return UserFollowResponseDto.builder()
//                .userId(follow.getUserId().getUserId())
//                .follower(follow.getFollower().getUserId())
//                .build();
//    }


}
