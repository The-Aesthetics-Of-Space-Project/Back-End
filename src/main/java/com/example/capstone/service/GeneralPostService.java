package com.example.capstone.service;

import com.example.capstone.dto.GeneralPostDetailResponseDto;
import com.example.capstone.dto.GeneralPostListResponseDto;
import com.example.capstone.repository.GeneralPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralPostService {
    private final GeneralPostRepository generalPostRepository;

    /**
     * 일반 게시물 전체 목록 조회
     */
    @Transactional
    public List<GeneralPostListResponseDto> getPosts() {
        return generalPostRepository.findAllByOrderByArticleIdDesc()
                .stream()
                .map(GeneralPostListResponseDto::createDto)
                .toList();
    }

    /**
     * 일반 게시물 상세 조회
     */
    @Transactional
    public GeneralPostDetailResponseDto getPost(Integer id) {
        return generalPostRepository.findById(id)
                .map(GeneralPostDetailResponseDto::createDto)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }
}
