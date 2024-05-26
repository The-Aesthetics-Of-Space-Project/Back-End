package com.example.capstone.entity.community.contest.article;

import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private Integer articleId;



    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = true)
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

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @OneToMany(mappedBy = "contestPost"
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private Set<ContestLike> contestLikes;

    @OneToMany(mappedBy = "contestPost"
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private Set<ContestComment> contestComments;

    public ContestPost updateContestPost(ContestPostUpdateRequestDto contestPostUpdateRequestDto){
//        contestId = contestPostUpdateRequestDto.getContest();
        title = contestPostUpdateRequestDto.getTitle();
        contents = contestPostUpdateRequestDto.getContents();
        return this;
    }
}

