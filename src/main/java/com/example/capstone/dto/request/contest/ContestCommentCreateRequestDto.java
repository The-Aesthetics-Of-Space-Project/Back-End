package com.example.capstone.dto.request.contest;

import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.contest.comment.ContestComment;
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
public class ContestCommentCreateRequestDto {
    private String content;
    private Integer parentId;
    private Integer contestId;
    private String nickname;


    public ContestComment toEntity(User user, ContestPost contestPost) {
        return ContestComment.builder()
                .content(content)
                .parentId(parentId)
                .contestPost(contestPost)
                .user(user)
                .date(DateTimeUtils.currentTime()) // 작성 시간 바로 엔티티에 들어감
                .build();
    }

}
