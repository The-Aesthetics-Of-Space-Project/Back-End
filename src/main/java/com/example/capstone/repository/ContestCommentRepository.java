package com.example.capstone.repository;

import com.example.capstone.entity.community.contest.comment.ContestComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestCommentRepository extends JpaRepository<ContestComment,Integer> {
}
