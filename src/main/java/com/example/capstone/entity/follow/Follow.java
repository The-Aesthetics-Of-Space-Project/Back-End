package com.example.capstone.entity.follow;


import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follow")
//@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class , property = "id")
public class Follow  {


    @EmbeddedId
    private FollowId id;

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
