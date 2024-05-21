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
public class Scrap {
    @EmbeddedId
    private ScrapId id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("generalPost")
    @JoinColumn(name = "article_id")
    private GeneralPost generalPost;
}

