package com.example.capstone.dto.response;

import com.example.capstone.entity.community.general.article.GeneralPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPostDetailResponseDto {
    private Integer articleId;
    private String title;
    private String thumbnail;
    private LocalDateTime date;
    private String content;
    private String nickname;
    private Integer likeCount;
    private Integer scrapCount;
    private String profile;
    private Boolean isLiked;
    private Boolean isScraped;

    /**
     * GeneralPost 엔티티 클래스를 GeneralPostDetailResponseDto로 변환
     */
    public static GeneralPostDetailResponseDto createDto(GeneralPost generalPost) {
        return GeneralPostDetailResponseDto.builder()
                .articleId(generalPost.getArticleId())
                .title(generalPost.getTitle())
                .thumbnail(generalPost.getThumbnail())
                .date(generalPost.getDate())
                .content(generalPost.getContent())
                .nickname(generalPost.getUser().getNickname())
                .likeCount(generalPost.getLikes().size())
                .scrapCount(generalPost.getScraps().size())
                .profile(generalPost.getUser().getProfile())
                .build();
    }
}
