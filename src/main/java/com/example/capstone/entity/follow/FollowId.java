package com.example.capstone.entity.follow;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class FollowId implements Serializable {




    private String userId;
//    private User user_id;



    private String follower;

}
