package com.example.capstone.controller;

import com.example.capstone.dto.GeneralPostListResponseDto;
import com.example.capstone.service.GeneralPostService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
