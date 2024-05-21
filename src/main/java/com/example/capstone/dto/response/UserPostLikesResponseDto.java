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
    private Integer articleId;

    public static UserPostLikesResponseDto createDto(GeneralLike like){
        return builder()
                .articleId(like.getGeneralPost().getArticleId())
                .build();
    }


}
