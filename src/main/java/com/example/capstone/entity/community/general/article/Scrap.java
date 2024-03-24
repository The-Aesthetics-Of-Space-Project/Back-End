package com.example.capstone.entity.community.general.article;

import com.example.capstone.entity.user.User;
import jakarta.persistence.*;

@Entity
@IdClass(ScrapId.class)
public class Scrap {
    @Id
    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "article_id")
    private GeneralArticle generalArticle;
}

