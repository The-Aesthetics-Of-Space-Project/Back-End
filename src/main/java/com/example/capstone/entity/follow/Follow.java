package com.example.capstone.entity.follow;


import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "follow")
//@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class , property = "id")
public class Follow  {


    @EmbeddedId
    private FollowId followId;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "user_id")
    private User userId;
//    @JoinColumn(name = "user_id")
//    private User userId;

    @MapsId("follower")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOWER", referencedColumnName = "user_id")
    private User follower;

}
