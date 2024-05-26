package com.example.capstone.repository;

import com.example.capstone.entity.community.contest.article.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest,Integer> {
}
