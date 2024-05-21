package com.example.capstone.repository;

import com.example.capstone.entity.community.general.article.GeneralPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralPostRepository extends JpaRepository<GeneralPost, Integer> {
    /**
     * 일반 게시물 전체 목록 조회
     */
    List<GeneralPost> findAllByOrderByArticleIdDesc();

    /**
     * articleId로 generalPost 찾는 메서드
     */
    Optional<GeneralPost> findByArticleId(Integer articleId);

    /**
     * 좋아요 수가 가장 많은 상위 3개의 게시물 목록 조회
     */
    @Query("SELECT p FROM GeneralPost p ORDER BY size(p.likes) DESC")
    List<GeneralPost> findTop3ByLikes(Pageable pageable);
}