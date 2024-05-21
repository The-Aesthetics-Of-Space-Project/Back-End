package com.example.capstone.repository;

import com.example.capstone.entity.follow.Follow;
import com.example.capstone.entity.follow.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    List<Follow> findByUserId_UserId(String userId);



    List<Follow> findByFollower_UserId(String userId);
    Long countByFollower_UserId(String userId);

    Long countByUserId_UserId(String userId);



}
