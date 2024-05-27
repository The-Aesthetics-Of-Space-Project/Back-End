package com.example.capstone.dto.response.contest;

import com.example.capstone.entity.community.contest.article.ContestPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContestPostsResponseDto {

    private Integer articleId;
    private Integer contestId;
    private String title;
    private String thumbnail;
    private String nickname;
    private Integer likeCount;

    public static UserContestPostsResponseDto createDto(ContestPost contestPost){
        return UserContestPostsResponseDto.builder()
                .articleId(contestPost.getArticleId())
                .contestId(contestPost.getContest().getContestId())
                .title(contestPost.getTitle())
                .thumbnail(contestPost.getThumbnail())
                .nickname(contestPost.getUser().getNickname())
                .likeCount(contestPost.getContestLikes().size())
                .build();
    }
}
