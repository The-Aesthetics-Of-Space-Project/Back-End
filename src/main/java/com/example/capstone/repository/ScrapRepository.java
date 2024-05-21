package com.example.capstone.repository;

import com.example.capstone.entity.community.general.article.Scrap;
import com.example.capstone.entity.community.general.article.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {
}
