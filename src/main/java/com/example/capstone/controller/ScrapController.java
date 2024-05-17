package com.example.capstone.controller;

import com.example.capstone.dto.request.ScrapRequestDto;
import com.example.capstone.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;

    /**
     * 일반 게시판 스크랩 등록
     */
    @PostMapping("/api/general/scrap")
    public ResponseEntity<String> scrapPost(@RequestBody ScrapRequestDto scrapRequestDto) {
        scrapService.scrapPost(scrapRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 스크랩에 성공했습니다.");
    }

    /**
     * 일반 게시판 스크랩 취소
     */
    @PostMapping("api/general/unscrap")
    public ResponseEntity<String> unscrapPost(@RequestBody ScrapRequestDto scrapRequestDto) {
        scrapService.unscrapPost(scrapRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 스크랩 취소에 성공했습니다.");
    }
}
