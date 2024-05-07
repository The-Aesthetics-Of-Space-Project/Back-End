package com.example.capstone.entity.user;

import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.comment.GeneralComment;
import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.Scrap;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.community.contest.article.ContestLike;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false, length = 30, columnDefinition = "VARCHAR_IGNORECASE")
    private String userId;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;


    /**
     * User와 ContestBoard 사이의 일대다 관계. 'user' 필드를 통해 열결됨.
     * FetchType.LAZY로 지연 로딩 설정, 실제 사용 시점에 ContestBoard 데이터 로드
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<GeneralPost> generalPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<GeneralComment> generalComments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<GeneralLike> generalLikes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Scrap> scraps;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ContestPost> contestPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ContestComment> contestComments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ContestLike> contestLikes;
}

