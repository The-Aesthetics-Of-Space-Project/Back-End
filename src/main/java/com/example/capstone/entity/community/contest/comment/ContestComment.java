package com.example.capstone.entity.community.contest.comment;

import com.example.capstone.dto.request.GeneralCommentUpdateRequestDto;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private LocalDateTime date;

    private String content;
    @Column(name = "parentId")
    private Integer parentId;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestPost contestPost;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;

    public void updateComment(String contents) {
        this.content = contents;
    }


}

