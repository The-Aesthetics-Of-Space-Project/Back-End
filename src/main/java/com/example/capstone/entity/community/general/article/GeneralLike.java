package com.example.capstone.entity.community.general.article;

import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"user", "generalPost"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(GeneralLikeId.class)
public class GeneralLike {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "article_id")
    private GeneralPost generalPost;
}

