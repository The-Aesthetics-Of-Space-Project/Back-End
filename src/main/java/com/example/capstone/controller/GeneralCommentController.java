package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralCommentCreateRequestDto;
import com.example.capstone.dto.request.GeneralCommentUpdateRequestDto;
import com.example.capstone.dto.response.GeneralCommentReadResponseDto;
import com.example.capstone.service.GeneralCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GeneralCommentController {
    private final GeneralCommentService generalCommentService;

    /**
     * 일반 게시판 댓글 등록
     */
    @PostMapping("/api/general/comment")
    public ResponseEntity<String> createComment(@RequestBody GeneralCommentCreateRequestDto generalCommentCreateRequestDto) {
        generalCommentService.createComment(generalCommentCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 등록에 성공했습니다.");
    }

    /**
     * 일반 게시판 댓글 삭제
     */
    @DeleteMapping("/api/general/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        generalCommentService.deleteComment(id);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제에 성공했습니다.");
    }

    /**
     * 일반 게시판 댓글 조회
     */
    @GetMapping("/api/general/comment/{id}")
    public GeneralCommentReadResponseDto readComment(@PathVariable Integer id) {
        return generalCommentService.readComment(id);
    }

    /**
     * 일반 게시판 댓글 수정
     */
    @PutMapping("/api/general/comment/{id}")
    public ResponseEntity<String> updateComment(
            @PathVariable Integer id,
            @RequestBody GeneralCommentUpdateRequestDto generalCommentUpdateRequestDto
    ) {
        generalCommentService.updateComment(id, generalCommentUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정에 성공했습니다.");
    }
}

