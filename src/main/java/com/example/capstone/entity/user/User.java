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
import lombok.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"followers", "followings", "generalPosts", "generalLikes", "scraps", "generalComments"})
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
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
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<GeneralPost> generalPosts;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<GeneralComment> generalComments;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<GeneralLike> generalLikes;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Scrap> scraps;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ContestPost> contestPosts;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ContestComment> contestComments;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ContestLike> contestLikes;



    @OneToMany(
            mappedBy = "follower",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Follow> followings;

    @OneToMany(
            mappedBy = "userId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Follow> followers;



    public void updateDetails(String userId,UserDetailsUpdateRequestDto userDetailsUpdateRequestDto){
        if (userDetailsUpdateRequestDto.getNickname()!=null){
            this.nickname = userDetailsUpdateRequestDto.getNickname();
        }
        if (userDetailsUpdateRequestDto.getProfile()!=null){
            this.profile = "http://119.198.33.129:8080/users/image?userId="+userId;
        }
        if (userDetailsUpdateRequestDto.getPassword()!=null){
            this.password = userDetailsUpdateRequestDto.getPassword();
        }

    }

    public void updatePassword(String password){
        this.password = password;
    }
}

