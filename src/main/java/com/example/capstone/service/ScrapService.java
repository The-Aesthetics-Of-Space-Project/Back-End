package com.example.capstone.service;

import com.example.capstone.dto.request.ScrapRequestDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.article.ScrapId;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.ScrapRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final GeneralPostRepository generalPostRepository;
    private final UserRepository userRepository;

    /**
     * 일반 게시판 스크랩 등록
     */
    @Transactional
    public void scrapPost(ScrapRequestDto scrapRequestDto) {
        GeneralPost generalPost = generalPostRepository.findByArticleId(scrapRequestDto.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));

        User user = userRepository.findByUserId(scrapRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));

        scrapRepository.save(scrapRequestDto.toEntity(user, generalPost));
    }

    /**
     * 일반 게시판 스크랩 취소
     */
    @Transactional
    public void unscrapPost(ScrapRequestDto scrapRequestDto) {
        // 사용자와 게시물 엔티티 조회
        User user = userRepository.findById(scrapRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));

        GeneralPost generalPost = generalPostRepository.findById(scrapRequestDto.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));

        // 복합 키 생성
        ScrapId id = new ScrapId(user.getUserId(), generalPost.getArticleId());

        // 스크랩 엔티티 조회
        scrapRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("스크랩이 존재하지 않습니다.")
        );

        scrapRepository.deleteById(id);
    }
}