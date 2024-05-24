package com.example.capstone.dto.response.contest;

import com.example.capstone.entity.community.contest.article.ContestPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestPostDetailResponseDto {
    private String contest;
    private Integer contestId;
    private String title;
    private String thumbnail;
    private LocalDateTime date;
    private String nickname;
    private Integer likeCount;
    private Integer scrapCount;


    public static ContestPostDetailResponseDto createDto(ContestPost contestPost){
        return ContestPostDetailResponseDto.builder()
                .contest(contestPost.getContest())
                .contestId(contestPost.getContestId())
                .title(contestPost.getTitle())
                .thumbnail(contestPost.getThumbnail())
                .date(contestPost.getDate())
                .nickname(contestPost.getUser().getNickname())
                .likeCount(contestPost.getContestLikes().size())
                .scrapCount(contestPost.getContestScraps().size())
                .build();
    }
}
