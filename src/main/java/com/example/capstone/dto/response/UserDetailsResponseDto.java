package com.example.capstone.dto.response;


import com.example.capstone.entity.user.User;
import lombok.*;

@Data
@Builder
@Setter // 임시
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponseDto {

    private String profile;

    private String nickname;

    // 유저 팔로워 ( 사용자를 팔로우 하는 사람의 수 )
    private Integer follower;

    // 유저 팔로잉 ( 사용자가 팔로우하는 사람의 수 )
    private Integer following;

    // 게시물 좋아요 ( 사용자가 좋아요 누른 게시글의 수 )
    private Integer likes;

    private Integer scraps;

    public static UserDetailsResponseDto createDto(User user){
        return UserDetailsResponseDto.builder()
                .profile(user.getProfile())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .follower(user.getFollowers().size())
                .following(user.getFollowings().size())
                .likes(user.getGeneralLikes().size()+user.getContestLikes().size())
                .scraps(user.getScraps().size())
                .build();
    }

}
