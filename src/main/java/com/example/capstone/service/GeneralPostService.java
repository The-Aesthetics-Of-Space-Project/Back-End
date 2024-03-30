package com.example.capstone.service;

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
                .map(GeneralPostListResponseDto::generalPostListResponseDto)
                .toList();
    }
}
