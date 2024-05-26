package com.example.capstone.entity.community.contest.article;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"contestPosts"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contestId;

    @Column
    private String contest;

    @Column
    private String contestPoster;

    @OneToMany(mappedBy = "contest",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<ContestPost> contestPosts;


}
