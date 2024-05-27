package com.example.capstone.dto.response;

import com.example.capstone.entity.community.general.article.GeneralPost;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPostListResponseDto {
    private Integer articleId;
    private String title;
    private String thumbnail;
    private String nickname;
    private Integer likeCount;
    private String profile;

    /**
     * GeneralPost 엔티티 클래스를 GeneralPostListResponseDto로 변환
     */
    public static GeneralPostListResponseDto createDto(GeneralPost generalPost) {
        return GeneralPostListResponseDto.builder()
                .articleId(generalPost.getArticleId())
                .title(generalPost.getTitle())
                .thumbnail(generalPost.getThumbnail())
                .nickname(generalPost.getUser().getNickname())
                .likeCount(generalPost.getLikes().size())
                .profile(generalPost.getUser().getProfile())
                .build();
    }
}
