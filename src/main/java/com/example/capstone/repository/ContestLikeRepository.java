package com.example.capstone.repository;

import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.community.contest.article.ContestLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestLikeRepository extends JpaRepository<ContestLike, ContestLikeId> {
}
