package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralPostCreateRequestDto;
import com.example.capstone.dto.response.GeneralPostDetailResponseDto;
import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.service.GeneralPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeneralPostController {
    private final GeneralPostService generalPostService;

    /**
     * 일반 게시물 전체 목록 조회
     */
    @GetMapping("/api/general/posts")
    public List<GeneralPostListResponseDto> getPosts() {
        return generalPostService.getPosts();
    }

    /**
     * 일반 게시물 상세 조회
     */
    @GetMapping("/api/general/post/{id}")
    public GeneralPostDetailResponseDto getPost(@PathVariable Integer id) {
        return generalPostService.getPost(id);
    }

    /**
     * 일반 게시물 등록
     */
    @PostMapping("/api/general/post")
    public ResponseEntity<String> createPost(@RequestBody GeneralPostCreateRequestDto generalPostCreateRequestDto) {
        generalPostService.createPost(generalPostCreateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 등록에 성공했습니다.");
    }

    /**
     * 일반 게시물 삭제
     */
    @DeleteMapping("/api/general/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        generalPostService.deletePost(id);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 삭제에 성공했습니다.");
    }
}

