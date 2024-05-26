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
public class UserPostsResponseDto {
    private String title;
    private String nickname;
    private String userId;
    private String thumbnail;
    private String profile;
    private Integer likeCount;
    private Integer articleId;

    public static UserPostsResponseDto createLikesDto(GeneralLike like){
        return builder()
                .title(like.getGeneralPost().getTitle())
                .thumbnail(like.getGeneralPost().getThumbnail())
                .userId(like.getGeneralPost().getUser().getUserId())
                .nickname(like.getGeneralPost().getUser().getNickname())
                .profile(like.getGeneralPost().getUser().getProfile())
                .likeCount(like.getGeneralPost().getLikes().size())
                .articleId(like.getGeneralPost().getArticleId())
                .build();
    }

    public static UserPostsResponseDto createScrapsDto(Scrap scrap){
        return builder()
                .title(scrap.getGeneralPost().getTitle())
                .thumbnail(scrap.getGeneralPost().getThumbnail())
                .userId(scrap.getGeneralPost().getUser().getUserId())
                .nickname(scrap.getGeneralPost().getUser().getNickname())
                .profile(scrap.getGeneralPost().getUser().getProfile())
                .likeCount(scrap.getGeneralPost().getLikes().size())
                .articleId(scrap.getGeneralPost().getArticleId())
                .build();
    }


}
