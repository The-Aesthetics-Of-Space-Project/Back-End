package com.example.capstone.controller;

import com.example.capstone.dto.response.GeneralPostDetailResponseDto;
import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.service.GeneralPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
