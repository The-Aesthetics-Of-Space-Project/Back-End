package com.example.capstone.entity.community.general.article;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class ScrapId implements Serializable {
    private String user;
    private Integer generalPost;
}
