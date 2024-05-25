package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralPostCreateRequestDto;
import com.example.capstone.dto.request.GeneralPostUpdateRequestDto;
import com.example.capstone.dto.response.GeneralPostDetailResponseDto;
import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.entity.community.general.article.GeneralLikeId;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralLikeRepository;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralPostService {
    private final GeneralPostRepository generalPostRepository;
    private final UserRepository userRepository;
    private final GeneralLikeRepository generalLikeRepository;

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
    public GeneralPostDetailResponseDto getPost(String userId, Integer id) {
        // 복합 키 생성
        GeneralLikeId generalLikeId = new GeneralLikeId(userId, id);
        Boolean isLike = generalLikeRepository.existsById(generalLikeId);

        GeneralPostDetailResponseDto generalPostDetailResponseDto = generalPostRepository.findById(id)
                .map(GeneralPostDetailResponseDto::createDto)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        generalPostDetailResponseDto.setIsLiked(isLike);

        return generalPostDetailResponseDto;
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

    /**
     * 일반 게시물 수정
     */
    @Transactional
    public void updatePost(Integer id, GeneralPostUpdateRequestDto generalPostUpdateRequestDto) {
        GeneralPost generalPost = generalPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
        generalPost.updatePost(generalPostUpdateRequestDto);
    }

    /**
     * 좋아요 수가 가장 많은 상위 3개의 게시물 목록 조회
     */
    @Transactional
    public List<GeneralPostListResponseDto> getPopularPosts() {
        Pageable pageable = PageRequest.of(0 , 3);
        List<GeneralPost> posts = generalPostRepository.findTop3ByLikes(pageable);
        return posts.stream()
                .map(GeneralPostListResponseDto::createDto)
                .toList();
    }
}

