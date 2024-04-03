package com.example.capstone.dto.response;

import com.example.capstone.entity.community.general.article.GeneralPost;
import lombok.*;
import java.sql.Blob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPostListResponseDto {
    private Integer articleId;
    private String title;
    private Blob thumbnail;
    private String nickname;
    private Integer likeCount;

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
                .build();
    }
}
