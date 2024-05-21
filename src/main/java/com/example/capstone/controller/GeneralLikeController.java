package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralLikeRequestDto;
import com.example.capstone.service.GeneralLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "GeneralLike Controller : 일반 게시판 좋아요", description = "General Like Controller")
    @Operation(summary = "좋아요 등록", description = "사용자가 좋아요를 등록할 때 사용하는 API")
    @PostMapping("/api/general/like")
    public ResponseEntity<String> likePost(@RequestBody GeneralLikeRequestDto generalLikeRequestDto) {
        generalLikeService.likePost(generalLikeRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("좋아요 등록에 성공했습니다.");
    }

    /**
     * 일반 게시판 좋아요 취소
     */
    @Tag(name = "GeneralLike Controller : 일반 게시판 좋아요", description = "General Like Controller")
    @Operation(summary = "좋아요 취소", description = "사용자가 좋아요를 취소할 때 사용하는 API")
    @PostMapping("/api/general/unlike")
    public ResponseEntity<String> unlikePost(@RequestBody GeneralLikeRequestDto generalLikeRequestDto) {
        generalLikeService.unlikePost(generalLikeRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("좋아요 취소에 성공했습니다.");
    }
}
