package com.example.capstone.dto.request;

import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.comment.GeneralComment;
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
public class GeneralCommentCreateRequestDto {
    private String content;
    private Integer parentId;
    private Integer articleId;
    private String nickname;

    /**
     * GeneralCommentCreateReqeustDto를 엔티티로 변환
     * @param user 댓글 작성자
     * @param generalPost 댓굴 작성 대상 게시물
     * @return GeneralComment 객체
     */
    public GeneralComment toEntity(User user, GeneralPost generalPost) {
        return GeneralComment.builder()
                .content(content)
                .parentId(parentId)
                .generalPost(generalPost)
                .user(user)
                .date(DateTimeUtils.currentTime()) // 작성 시간 바로 엔티티에 들어감
                .build();
    }
}
