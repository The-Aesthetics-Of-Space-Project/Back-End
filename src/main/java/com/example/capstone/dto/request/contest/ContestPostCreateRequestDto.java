package com.example.capstone.dto.request.contest;

import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.util.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestPostCreateRequestDto {
    private String contest;
    private String title;
    private MultipartFile thumbnail;
    private String contents;
    private String nickname;

    public ContestPost toEntity(User user){
        return  ContestPost.builder()
                .contest(contest)
                .title(title)
                .date(DateTimeUtils.currentTime())
                .contents(contents)
                .user(user)
                .build();
    }
}
