package com.example.capstone.service;


import com.example.capstone.dto.request.contest.ContestLikeRequestDto;
import com.example.capstone.entity.community.contest.article.ContestLikeId;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContestLikeService {

    private final ContestLikeRepository contestLikeRepository;
    private final ContestPostRepository contestPostRepository;
    private final UserRepository userRepository;

    /**
     * 일반 게시판 좋아요 등록
     */
    @Transactional
    public void likeContestPost(ContestLikeRequestDto contestLikeRequestDto) {
        ContestPost contestPost = contestPostRepository.findById(contestLikeRequestDto.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));

        User user = userRepository.findByUserId(contestLikeRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));

        contestLikeRepository.save(contestLikeRequestDto.toEntity(user, contestPost));
    }

    /**
     * 일반 게시판 좋아요 취소
     */
    @Transactional
    public void unlikeContestPost(ContestLikeRequestDto contestLikeRequestDto) {
        // 사용자와 게시물 엔티티 조회
        User user = userRepository.findById(contestLikeRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));
        ContestPost contestPost = contestPostRepository.findById(contestLikeRequestDto.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));

        // 복합 키 생성
        ContestLikeId id = new ContestLikeId(user.getUserId(), contestPost.getArticleId());

        // 좋아요 엔티티 조회
        contestLikeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("좋아요가 존재하지 않습니다.")
        );

        contestLikeRepository.deleteById(id);
    }
}
