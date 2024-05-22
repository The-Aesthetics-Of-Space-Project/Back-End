package com.example.capstone.dto.response;

import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.Scrap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGeneralPostResponseDto {
    private String title;
    private String userId;
    private String thumbnail;
    private String profile;

    public static UserGeneralPostResponseDto createLikesDto(GeneralLike like){
        return builder()
                .title(like.getGeneralPost().getTitle())
                .thumbnail(like.getGeneralPost().getThumbnail())
                .userId(like.getGeneralPost().getUser().getUserId())
                .profile(like.getGeneralPost().getUser().getProfile())
                .build();
    }

    public static UserGeneralPostResponseDto createScrapsDto(Scrap scrap){
        return builder()
                .title(scrap.getGeneralPost().getTitle())
                .thumbnail(scrap.getGeneralPost().getThumbnail())
                .userId(scrap.getGeneralPost().getUser().getUserId())
                .profile(scrap.getGeneralPost().getUser().getProfile())
                .build();
    }


}
