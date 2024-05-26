package com.example.capstone.entity.community.contest.article;

import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"user", "contestPost"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestLike {
    @EmbeddedId
    ContestLikeId id;

    @MapsId("contestPost")
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ContestPost contestPost;

    @MapsId("user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

