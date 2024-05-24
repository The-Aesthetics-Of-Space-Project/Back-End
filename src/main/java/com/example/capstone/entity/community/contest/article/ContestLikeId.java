package com.example.capstone.entity.community.contest.article;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContestLikeId implements Serializable {
    private String user;
    private Integer contestPost;

}
