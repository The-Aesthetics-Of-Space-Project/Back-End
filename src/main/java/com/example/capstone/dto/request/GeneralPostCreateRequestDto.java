package com.example.capstone.dto.request;

import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.util.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPostCreateRequestDto {
    private String title;
    private String thumbnail;
    private String content;
    private String nickname;

    /**
     * GeneralPostCreateRequestDto를 엔티티로 변환
     */
    public GeneralPost toEntity(User user) {
        return GeneralPost.builder()
                .title(title)
                .thumbnail(thumbnail)
                .content(content)
                .date(DateTimeUtils.currentTime()) // 작성 시간 바로 엔티티에 들어감
                .user(user)
                .build();
    }
}
