package com.example.capstone.repository;

import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.article.Scrap;
import com.example.capstone.entity.community.general.article.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {

    Long countByUser_UserId(String userId);

    List<Scrap> findByUser_UserId(String userId);

}
