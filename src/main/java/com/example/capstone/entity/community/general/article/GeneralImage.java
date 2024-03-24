package com.example.capstone.entity.community.general.article;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
public class GeneralImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @Lob
    @Column(nullable = false)
    private Blob text;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private GeneralArticle generalArticle;
}

