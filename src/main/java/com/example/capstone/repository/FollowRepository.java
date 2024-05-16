package com.example.capstone.repository;

import com.example.capstone.entity.follow.Follow;
import com.example.capstone.entity.follow.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    List<Follow> findByUserId_UserId(String email);
//    List<Follow> findByNickname_Nickname(String nickname);



    List<Follow> findByFollower_UserId(String email);
//    List<Follow> findByFollower_Nickname(String nickname);





//    List<Follow> findByUserId_UserId(String email);
//
//    List<Follow> findByFollower_UserId(String email);

}
