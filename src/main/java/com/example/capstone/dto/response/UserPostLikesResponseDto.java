package com.example.capstone.dto.response;

import com.example.capstone.entity.community.general.article.GeneralLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostLikesResponseDto {
    private String thumbnail;
    private Integer articleId;

//    public UserPostLikesResponseDto createDto(GeneralLike like){
//        return UserPostLikesResponseDto.builder()
//                .articleId(like.getId())
//    }
}
