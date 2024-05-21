package com.example.capstone.dto.response;

import com.example.capstone.entity.community.general.article.GeneralLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostLikesResponseDto {
    private String title;
    private String userId;
    private String thumbnail;
    private String profile;

    public static UserPostLikesResponseDto createDto(GeneralLike like){
        return builder()
                .title(like.getGeneralPost().getTitle())
                .thumbnail(like.getGeneralPost().getThumbnail())
                .userId(like.getGeneralPost().getUser().getUserId())
                .profile(like.getGeneralPost().getUser().getProfile())
                .build();
    }


}
