package com.example.capstone.entity.community.general.article;

import com.example.capstone.entity.community.general.comment.GeneralComment;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Blob;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class GeneralPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = true) // DB 테스트를 위해 임시로 false->true 변경
    private Blob thumbnail;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;

    /**
     * Board와 BoardLike 사이의 일대다 관계. 'Board' 필드를 통해 연결됨.
     * Fetch.EAGER로 즉시 로드
     * 좋아요는 게시물 목록 조회, 상세 조회에서 항상 노출
     */
    @OneToMany(mappedBy = "generalPost", fetch = FetchType.EAGER)
    private Set<GeneralLike> likes;

    @OneToMany(mappedBy = "generalPost", fetch = FetchType.LAZY)
    private Set<GeneralImage> images;

    @OneToMany(mappedBy = "generalPost", fetch = FetchType.LAZY)
    private Set<Scrap> scraps;

    @OneToMany(mappedBy = "generalPost", fetch = FetchType.LAZY)
    private Set<GeneralComment> comments;
}
