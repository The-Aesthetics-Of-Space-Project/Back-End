package com.example.capstone.entity.community.contest.article;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
public class ContestImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @Lob
    @Column(nullable = false)
    private Blob text;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestArticle contestArticle;
}
