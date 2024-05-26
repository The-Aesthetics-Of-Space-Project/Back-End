package com.example.capstone.dto.response.contest;

import com.example.capstone.entity.community.contest.article.ContestPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ContestPostDetailResponseDto {
    private Integer contestId;
    private Integer articleId;
    private String title;
    private String contents;
    private String thumbnail;
    private String profile;
    private LocalDateTime date;
    private String nickname;
    private Integer likeCount;
    private Integer commentCount;
    private boolean isLike;


    public static ContestPostDetailResponseDto createDto(ContestPost contestPost){
        log.info("ConPoDeReDto"+contestPost.getUser().getUserId());
        return ContestPostDetailResponseDto.builder()
                .contestId(contestPost.getContest().getContestId())
                .articleId(contestPost.getArticleId())
                .title(contestPost.getTitle())
                .contents(contestPost.getContents())
                .thumbnail(contestPost.getThumbnail())
                .profile(contestPost.getUser().getProfile())
                .date(contestPost.getDate())
                .nickname(contestPost.getUser().getNickname())
                .likeCount(contestPost.getContestLikes().size())
                .commentCount(contestPost.getContestComments().size())
                .build();
    }
}
