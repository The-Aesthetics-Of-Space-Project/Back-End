package com.example.capstone.entity.community.general.comment;

import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String content;

    @Column(name = "parentId")
    private Integer parentId;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private GeneralPost generalPost;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;
}

