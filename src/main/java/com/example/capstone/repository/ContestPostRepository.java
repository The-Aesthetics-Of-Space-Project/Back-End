package com.example.capstone.repository;


import com.example.capstone.entity.community.contest.article.ContestPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestPostRepository extends JpaRepository<ContestPost,Integer> {
    List<ContestPost> findAllByOrderByContestIdDesc();
}
