package com.example.capstone.entity.community.general.article;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ScrapId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private String user;

    @Column(name = "article_id", nullable = false)
    private Integer generalPost;
}
