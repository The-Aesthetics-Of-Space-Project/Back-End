package com.example.capstone.repository;

import com.example.capstone.entity.community.general.article.GeneralPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GeneralPostRepository extends JpaRepository<GeneralPost, Integer> {
    /**
     * 일반 게시물 전체 목록 조회
     */
    List<GeneralPost> findAllByOrderByArticleIdDesc();
}
