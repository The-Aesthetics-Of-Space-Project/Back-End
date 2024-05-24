package com.example.capstone.dto.response.contest;

import com.example.capstone.dto.response.GeneralCommentReadResponseDto;
import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.community.general.comment.GeneralComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestCommentResponseDto {
    private Integer commentId;
    private String content;
    private Integer parentId;
    private LocalDateTime date;
    private Integer contestId;
    private String nickname;


    public static ContestCommentResponseDto createDto(ContestComment contestComment) {
        return ContestCommentResponseDto.builder()
                .commentId(contestComment.getCommentId())
                .content(contestComment.getContent())
                .parentId(contestComment.getParentId())
                .date(contestComment.getDate())
                .contestId(contestComment.getContestPost().getContestId())
                .nickname(contestComment.getUser().getNickname())
                .build();
    }
}
