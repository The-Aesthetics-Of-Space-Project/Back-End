package com.example.capstone.repository;

import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.GeneralLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralLikeRepository extends JpaRepository<GeneralLike, GeneralLikeId> {
}