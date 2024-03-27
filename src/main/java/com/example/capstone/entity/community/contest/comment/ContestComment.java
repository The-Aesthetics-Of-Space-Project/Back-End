package com.example.capstone.entity.community.contest.comment;

import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ContestComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private Date date;

    private String content;

    private Integer depth;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "`group`")
    private Integer group;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestPost contestPost;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;
}

