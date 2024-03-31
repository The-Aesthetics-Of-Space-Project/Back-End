package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralPostCreateRequestDto;
import com.example.capstone.dto.response.GeneralPostDetailResponseDto;
import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralPostService {
    private final GeneralPostRepository generalPostRepository;
    private final UserRepository userRepository;

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

    /**
     * 일반 게시물 등록
     */
    @Transactional
    public void createPost(GeneralPostCreateRequestDto generalPostCreateRequestDto) {
        User user = userRepository.findByNickname(generalPostCreateRequestDto.getNickname())
                   .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        generalPostRepository.save(generalPostCreateRequestDto.toEntity(user));
    }

    /**
     * 일반 게시물 삭제
     */
    @Transactional
    public void deletePost(Integer id) {
        generalPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        generalPostRepository.deleteById(id);
    }
}
