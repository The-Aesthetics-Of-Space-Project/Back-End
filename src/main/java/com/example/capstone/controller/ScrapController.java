package com.example.capstone.controller;

import com.example.capstone.dto.request.ScrapRequestDto;
import com.example.capstone.service.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Scrap Controller : 일반 게시판 스크랩", description = "Scrap Controller")
    @Operation(summary = "스크랩 등록", description = "사용자가 스크랩을 등록할 때 사용하는 API")
    @PostMapping("/api/general/scrap")
    public ResponseEntity<String> scrapPost(@RequestBody ScrapRequestDto scrapRequestDto) {
        scrapService.scrapPost(scrapRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 스크랩에 성공했습니다.");
    }

    /**
     * 일반 게시판 스크랩 취소
     */
    @Tag(name = "Scrap Controller : 일반 게시판 스크랩", description = "Scrap Controller")
    @Operation(summary = "스크랩 취소", description = "사용자가 스크랩을 취소할 때 사용하는 API")
    @PostMapping("api/general/unscrap")
    public ResponseEntity<String> unscrapPost(@RequestBody ScrapRequestDto scrapRequestDto) {
        scrapService.unscrapPost(scrapRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 스크랩 취소에 성공했습니다.");
    }
}
