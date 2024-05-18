package com.example.capstone.dto.request;

import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.GeneralLikeId;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralLikeRequestDto {
    private String userId;
    private Integer articleId;

    /**
     * GeneralLikeRequestDto를 엔티티로 변환
     * @param user 좋아요 클릭커
     * @param generalPost 좋아요 대상 게시물
     * return GeneralLike 객체
     */
    public GeneralLike toEntity(User user, GeneralPost generalPost) {
        return GeneralLike.builder()
                .id(new GeneralLikeId(user.getUserId(), generalPost.getArticleId()))
                .user(user)
                .generalPost(generalPost)
                .build();
    }
}
