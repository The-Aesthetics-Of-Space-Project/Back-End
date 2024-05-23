package com.example.capstone.entity.community.contest.article;

import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Entity
@Data
@EqualsAndHashCode(exclude = {"contestComments", "contestLikes"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contestId;

    @Column(nullable = false, length = 20)
    private String contest;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime date;

//    @Column(nullable = false, length = 30)
//    private String address;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;

    @OneToMany(mappedBy = "contestPost"
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private Set<ContestImage> contestImages;

    @OneToMany(mappedBy = "contestPost"
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private Set<ContestLike> contestLikes;

    @OneToMany(mappedBy = "contestPost"
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private Set<ContestComment> contestComments;

    @OneToMany(mappedBy = "contestPost"
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private Set<ContestScrap> contestScraps;

    public void updateContestPost(ContestPostUpdateRequestDto contestPostUpdateRequestDto){
        contest = contestPostUpdateRequestDto.getContest();
        title = contestPostUpdateRequestDto.getTitle();
        thumbnail = contestPostUpdateRequestDto.getThumbnail();
        contents = contestPostUpdateRequestDto.getContents();
    }
}

