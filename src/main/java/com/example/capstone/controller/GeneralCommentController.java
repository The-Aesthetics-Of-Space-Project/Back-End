package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralCommentCreateRequestDto;
import com.example.capstone.dto.request.GeneralCommentUpdateRequestDto;
import com.example.capstone.dto.response.GeneralCommentReadResponseDto;
import com.example.capstone.service.GeneralCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeneralCommentController {
    private final GeneralCommentService generalCommentService;

    /**
     * 일반 게시판 댓글 등록
     */
    @Tag(name = "GeneralComment Controller : 일반 게시판 댓글", description = "General Comment Controller")
    @Operation(summary = "댓글 등록", description = "사용자가 댓글을 등록할 때 사용하는 API")
    @PostMapping("/api/general/comment")
    public ResponseEntity<String> createComment(@RequestBody GeneralCommentCreateRequestDto generalCommentCreateRequestDto) {
        generalCommentService.createComment(generalCommentCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 등록에 성공했습니다.");
    }

    /**
     * 일반 게시판 댓글 삭제
     */
    @Tag(name = "GeneralComment Controller : 일반 게시판 댓글", description = "General Comment Controller")
    @Operation(summary = "댓글 삭제", description = "사용자가 댓글을 삭제할 때 사용하는 API")
    @DeleteMapping("/api/general/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        generalCommentService.deleteComment(id);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제에 성공했습니다.");
    }

    /**
     * 일반 게시판 댓글 조회
     */
    @Tag(name = "GeneralComment Controller : 일반 게시판 댓글", description = "General Comment Controller")
    @Operation(summary = "댓글 조회", description = "사용자가 댓글을 조회할 때 사용하는 API")
    @GetMapping("/api/general/comment/{id}")
    public GeneralCommentReadResponseDto readComment(@PathVariable Integer id) {
        return generalCommentService.readComment(id);
    }

    /**
     * 일반 게시판 댓글 수정
     */
    @Tag(name = "GeneralComment Controller : 일반 게시판 댓글", description = "General Comment Controller")
    @Operation(summary = "댓글 수정", description = "사용자가 댓글을 수정할 때 사용하는 API")
    @PutMapping("/api/general/comment/{id}")
    public ResponseEntity<String> updateComment(
            @PathVariable Integer id,
            @RequestBody GeneralCommentUpdateRequestDto generalCommentUpdateRequestDto
    ) {
        generalCommentService.updateComment(id, generalCommentUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정에 성공했습니다.");
    }

    /**
     * 일반 게시판 댓글 전체 목록 조회
     */
    @Tag(name = "GeneralComment Controller : 일반 게시판 댓글", description = "General Comment Controller")
    @Operation(summary = "댓글 전체 목록 조회", description = "사용자가 댓글 전체 목록을 조회할 때 사용하는 API")
    @GetMapping("/api/general/comment/list/{id}")
    public List<GeneralCommentReadResponseDto> getComments(@PathVariable Integer id) {
        return generalCommentService.getComments(id);
    }
}

