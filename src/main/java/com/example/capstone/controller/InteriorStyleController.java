package com.example.capstone.controller;

import com.example.capstone.dto.response.InteriorStyleResponseDto;
import com.example.capstone.service.InteriorStyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InteriorStyleController {
    private final InteriorStyleService interiorStyleService;

    /**
     * 인테리어 스타일 테스트 결과 조회
     */
    @GetMapping("/api/interior/style/{id}")
    public InteriorStyleResponseDto getStyle(@PathVariable Integer id) {
        return interiorStyleService.getStyle(id);
    }
}
