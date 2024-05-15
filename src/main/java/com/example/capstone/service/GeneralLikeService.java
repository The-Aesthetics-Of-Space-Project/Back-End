package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralLikeRequestDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralLikeRepository;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        User user = userRepository.findByUserId(generalLikeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        generalLikeRepository.save(generalLikeRequestDto.toEntity(user, generalPost));
    }
}
