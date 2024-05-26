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
public class ContestPostsResponseDto {

    private Integer articleId;
    private Integer contestId;
    private String title;
    private String thumbnail;
    private String nickname;
    private Integer likeCount;
    private String profile;


    public static ContestPostsResponseDto createDto(ContestPost contestPost){
        return ContestPostsResponseDto.builder()
                .articleId(contestPost.getArticleId())
                .contestId(contestPost.getContest().getContestId())
                .title(contestPost.getTitle())
                .thumbnail(contestPost.getThumbnail())
                .nickname(contestPost.getUser().getNickname())
                .likeCount(contestPost.getContestLikes().size())
                .profile(contestPost.getUser().getProfile())
                .build();
    }
}
