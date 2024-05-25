package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralLikeRequestDto;
import com.example.capstone.entity.community.general.article.GeneralLikeId;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralLikeRepository;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralLikeService {
    private final GeneralLikeRepository generalLikeRepository;
    private final GeneralPostRepository generalPostRepository;
    private final UserRepository userRepository;

    /**
     * 일반 게시판 좋아요 등록
     */
    @Transactional
    public void likePost(GeneralLikeRequestDto generalLikeRequestDto) {
        GeneralPost generalPost = generalPostRepository.findByArticleId(generalLikeRequestDto.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));

        User user = userRepository.findByUserId(generalLikeRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));

        generalLikeRepository.save(generalLikeRequestDto.toEntity(user, generalPost));
    }

    /**
     * 일반 게시판 좋아요 취소
     */
    @Transactional
    public void unlikePost(GeneralLikeRequestDto generalLikeRequestDto) {
        // 사용자와 게시물 엔티티 조회
        User user = userRepository.findById(generalLikeRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));
        GeneralPost generalPost = generalPostRepository.findById(generalLikeRequestDto.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));

        // 복합 키 생성
        GeneralLikeId id = new GeneralLikeId(user.getUserId(), generalPost.getArticleId());

        // 좋아요 엔티티 조회
        generalLikeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("좋아요가 존재하지 않습니다.")
        );

        generalLikeRepository.deleteById(id);
    }
}
