package com.example.capstone.repository;

import com.example.capstone.entity.community.general.article.GeneralImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralImageRepository extends JpaRepository<GeneralImage, Integer> {
}