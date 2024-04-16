package com.example.capstone.repository;

import com.example.capstone.entity.community.general.comment.GeneralComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralCommentRepository extends JpaRepository<GeneralComment, Integer> {
}
