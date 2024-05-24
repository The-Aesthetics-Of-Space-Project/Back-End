package com.example.capstone.repository;


import com.example.capstone.entity.community.contest.article.ContestImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestImageRepository extends JpaRepository<ContestImage, Integer> {
}
