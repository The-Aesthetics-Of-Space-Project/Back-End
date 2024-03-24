package com.example.capstone.entity.community.contest.article;

import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class ContestArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contestId;

    @Column(nullable = false, length = 20)
    private String contest;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private byte[] thumbnail;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, length = 30)
    private String address;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;

    @OneToMany(mappedBy = "contestArticle", fetch = FetchType.LAZY)
    private Set<ContestImage> contestImages;

    @OneToMany(mappedBy = "contestArticle", fetch = FetchType.LAZY)
    private Set<ContestLike> contestLikes;

    @OneToMany(mappedBy = "contestArticle", fetch = FetchType.LAZY)
    private Set<ContestComment> contestComments;
}

