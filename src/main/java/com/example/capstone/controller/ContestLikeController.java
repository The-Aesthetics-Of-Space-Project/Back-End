package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralLikeRequestDto;
import com.example.capstone.dto.request.contest.ContestLikeRequestDto;
import com.example.capstone.service.ContestLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contest")
public class ContestLikeController {

    @Autowired
    private ContestLikeService contestLikeService;

    /**
     * 공모전 게시글 좋아요 등록
     */
    @Tag(name = "ContestLike Controller : 공모전 게시판 좋아요", description = "Contest Like Controller")
    @Operation(summary = "좋아요 등록", description = "사용자가 좋아요를 등록할 때 사용하는 API")
    @PostMapping("/like")
    public ResponseEntity<String> likePost(@RequestBody ContestLikeRequestDto contestLikeRequestDto) {
        contestLikeService.likeContestPost(contestLikeRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("좋아요 등록에 성공했습니다.");
    }

    /**
     * 공모전 게시글 좋아요 취소
     */
    @Tag(name = "ContestLike Controller : 공모전 게시판 좋아요", description = "Contest Like Controller")
    @Operation(summary = "좋아요 취소", description = "사용자가 좋아요를 취소할 때 사용하는 API")
    @PostMapping("/unlike")
    public ResponseEntity<String> unlikePost(@RequestBody ContestLikeRequestDto contestLikeRequestDto) {
        contestLikeService.unlikeContestPost(contestLikeRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("좋아요 취소에 성공했습니다.");
    }
}
