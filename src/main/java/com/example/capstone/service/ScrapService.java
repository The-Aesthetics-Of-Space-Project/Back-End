package com.example.capstone.service;

import com.example.capstone.dto.request.ScrapRequestDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.ScrapRepository;
import com.example.capstone.repository.UserRepository;
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
     * 일반 게시판 스크랩
     */
    @Transactional
    public void scrapPost(ScrapRequestDto scrapRequestDto) {
        GeneralPost generalPost = generalPostRepository.findByArticleId(scrapRequestDto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        User user = userRepository.findByUserId(scrapRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        scrapRepository.save(scrapRequestDto.toEntity(user, generalPost));
    }
}
