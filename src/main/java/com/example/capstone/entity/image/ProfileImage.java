package com.example.capstone.entity.image;

import com.example.capstone.entity.user.User;
import jakarta.persistence.*;

public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    public void updateUl(String url){
        this.url = url;
    }
}
