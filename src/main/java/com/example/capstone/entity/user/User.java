package com.example.capstone.entity.user;

import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.comment.GeneralComment;
import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.Scrap;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.follow.Follow;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private String profile;

    /**
     * User와 ContestBoard 사이의 일대다 관계. 'user' 필드를 통해 열결됨.
     * FetchType.LAZY로 지연 로딩 설정, 실제 사용 시점에 ContestBoard 데이터 로드
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
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



    @OneToMany(
            mappedBy = "follower",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Follow> followers;

    @OneToMany(
            mappedBy = "userId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Follow> followedUser;



    public void updateDetails(UserDetailsUpdateRequestDto userDetailsUpdateRequestDto){
        this.nickname = userDetailsUpdateRequestDto.getNickname();
    }
}

