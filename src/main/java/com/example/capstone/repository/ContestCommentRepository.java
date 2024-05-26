package com.example.capstone.repository;

import com.example.capstone.entity.community.contest.comment.ContestComment;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestCommentRepository extends JpaRepository<ContestComment,Integer> {

    List<ContestComment> findByContestPost_ArticleId(Integer id);

}
