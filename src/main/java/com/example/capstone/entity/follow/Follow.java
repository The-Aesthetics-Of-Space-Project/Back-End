package com.example.capstone.entity.follow;


import com.example.capstone.entity.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "follow")
@IdClass(FollowId.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class , property = "id")
public class Follow {


    @Id
    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User nickname;
//    @JoinColumn(name = "user_id")
//    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "follow", referencedColumnName = "nickname")
    private User follower;

}
