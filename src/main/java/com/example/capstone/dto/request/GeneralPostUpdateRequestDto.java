package com.example.capstone.dto.request;

import com.example.capstone.entity.community.general.article.GeneralPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPostUpdateRequestDto {
    private Integer articleId;
    private String title;
    private Blob thumbnail;
    private String content;

    /**
     * GeneralPostUpdateRequestDto를 엔티티로 변환
     */
    public GeneralPost toEntity() {
        return GeneralPost.builder()
                .articleId(articleId)
                .title(title)
                .thumbnail(thumbnail)
                .content(content)
                .build();
    }
}
