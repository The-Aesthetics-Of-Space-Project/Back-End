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

    private Integer contestId;
    private String contest;
    private String title;
    private String thumbnail;
    private String nickname;
    private Integer likeCount;


    public static ContestPostsResponseDto createDto(ContestPost contestPost){
        return ContestPostsResponseDto.builder()
                .contestId(contestPost.getContestId())
                .contest(contestPost.getContest())
                .title(contestPost.getTitle())
                .thumbnail(contestPost.getThumbnail())
                .likeCount(contestPost.getContestLikes().size())
                .build();
    }
}
