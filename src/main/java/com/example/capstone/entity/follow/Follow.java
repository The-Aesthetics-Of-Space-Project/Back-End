package com.example.capstone.entity.follow;


import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "follow")
@IdClass(FollowId.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class , property = "id")
public class Follow {


    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "user_id")
    private User userId;
//    @JoinColumn(name = "user_id")
//    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "FOLLOW", referencedColumnName = "user_id")
    private User follower;

}
