package com.example.capstone.dto.request;

import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.article.Scrap;
import com.example.capstone.entity.community.general.article.ScrapId;
import com.example.capstone.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapRequestDto {
    private String userId;
    private Integer articleId;

    /**
     * ScrapRequestDto를 엔티티로 변환
     * @param user 스크랩 클릭커
     * @param generalPost 스크랩 대상 게시물
     * return Scrap 객체
     */
    public Scrap toEntity(User user, GeneralPost generalPost) {
        return Scrap.builder()
                .id(new ScrapId(user.getUserId(), generalPost.getArticleId()))
                .user(user)
                .generalPost(generalPost)
                .build();
    }
}
