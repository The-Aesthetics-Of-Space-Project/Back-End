package com.example.capstone.entity.community.contest.article;

import com.example.capstone.entity.user.User;
import jakarta.persistence.*;

@Entity
public class ContestScrap {
    @EmbeddedId
    private ContestScrapId id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("contestPost")
    @JoinColumn(name = "contest_id")
    private ContestPost contestPost;

}
