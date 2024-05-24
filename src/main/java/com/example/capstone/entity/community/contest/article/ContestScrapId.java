package com.example.capstone.entity.community.contest.article;

import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContestScrapId implements Serializable {
    public Integer contestPost;

    public String user;
}
