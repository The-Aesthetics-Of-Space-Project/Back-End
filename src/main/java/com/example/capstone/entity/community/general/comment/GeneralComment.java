package com.example.capstone.entity.community.general.comment;

import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class GeneralComment {
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
    @JoinColumn(name = "article_id")
    private GeneralPost generalPost;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;
}

