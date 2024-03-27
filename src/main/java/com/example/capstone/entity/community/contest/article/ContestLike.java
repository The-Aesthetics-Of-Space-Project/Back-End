package com.example.capstone.entity.community.contest.article;

import com.example.capstone.entity.user.User;
import jakarta.persistence.*;

@Entity
@IdClass(ContestLikeId.class)
public class ContestLike {
    @Id
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestPost contestPost;

    @Id
    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;
}

