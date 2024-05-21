package com.example.capstone.dto.response;

import com.example.capstone.entity.community.general.comment.GeneralComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralCommentReadResponseDto {
    private Integer commentId;
    private String content;
    private Integer parentId;
    private LocalDateTime date;
    private Integer articleId;
    private String nickname;

    /**
     * GeneralComment 엔티티 클래스를 GeneralCommentReadResponseDto로 변환
     */
    public static GeneralCommentReadResponseDto toDto(GeneralComment generalComment) {
        return GeneralCommentReadResponseDto.builder()
                .articleId(generalComment.getCommentId())
                .content(generalComment.getContent())
                .parentId(generalComment.getParentId())
                .date(generalComment.getDate())
                .articleId(generalComment.getGeneralPost().getArticleId())
                .nickname(generalComment.getUser().getNickname())
                .build();
    }
}
