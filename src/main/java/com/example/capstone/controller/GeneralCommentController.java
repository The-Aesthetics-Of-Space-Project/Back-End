package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralCommentCreateRequestDto;
import com.example.capstone.service.GeneralCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
