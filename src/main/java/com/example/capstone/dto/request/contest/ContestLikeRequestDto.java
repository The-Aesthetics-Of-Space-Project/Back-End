package com.example.capstone.dto.request.contest;

import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.community.contest.article.ContestLikeId;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestLikeRequestDto {

    private String userId;
    private Integer articleId;

    public ContestLike toEntity(User user, ContestPost contestPost) {
        return ContestLike.builder()
                .id(new ContestLikeId(user.getUserId(), contestPost.getArticleId()))
                .user(user)
                .contestPost(contestPost)
                .build();
    }
}
