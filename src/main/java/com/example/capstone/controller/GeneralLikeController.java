package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralLikeRequestDto;
import com.example.capstone.service.GeneralLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GeneralLikeController {
    private final GeneralLikeService generalLikeService;

    /**
     * 일반 게시판 좋아요 등록
     */
    @PostMapping("/api/general/like")
    public ResponseEntity<String> likePost(@RequestBody GeneralLikeRequestDto generalLikeRequestDto) {
        generalLikeService.likePost(generalLikeRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("좋아요 등록에 성공했습니다.");
    }

    /**
     * 일반 게시판 좋아요 취소
     */
    @PostMapping("/api/general/unlike")
    public ResponseEntity<String> unlikePost(@RequestBody GeneralLikeRequestDto generalLikeRequestDto) {
        generalLikeService.unlikePost(generalLikeRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("좋아요 취소에 성공했습니다.");
    }
}
