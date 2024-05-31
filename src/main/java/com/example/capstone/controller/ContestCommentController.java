package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralCommentCreateRequestDto;
import com.example.capstone.dto.request.GeneralCommentUpdateRequestDto;
import com.example.capstone.dto.request.contest.ContestCommentCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestCommentUpdateRequestDto;
import com.example.capstone.dto.response.GeneralCommentReadResponseDto;
import com.example.capstone.dto.response.contest.ContestCommentResponseDto;
import com.example.capstone.service.ContestCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contest")
public class ContestCommentController {
    @Autowired
    private ContestCommentService contestCommentService;

    /**
     * 공모전 게시판 댓글 등록
     */
    @Tag(name = "ContestComment Controller : 공모전 게시글 댓글", description = "Contest Comment Controller")
    @Operation(summary = "공모전 댓글 등록", description = "사용자가 공모전 게시글의 댓글을 등록할 때 사용하는 API")
    @PostMapping("/comment")
    public ResponseEntity<String> createContestComment(@RequestBody ContestCommentCreateRequestDto contestCommentCreateRequestDto) {
        contestCommentService.createContestComment(contestCommentCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 등록에 성공했습니다.");
    }

    /**
     * 공모전 게시판 댓글 삭제
     */

    @Tag(name = "ContestComment Controller : 공모전 게시글 댓글", description = "Contest Comment Controller")
    @Operation(summary = "공모전 게시글 댓글 삭제", description = "사용자가 공모전 게시글의 댓글을 삭제할 때 사용하는 API")
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteContestComment(@PathVariable Integer id) {
        contestCommentService.deleteContestComment(id);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제에 성공했습니다.");
    }

    /**
     * 공모전 게시판 댓글 조회
     */
    @Tag(name = "ContestComment Controller : 공모전 게시글 댓글", description = "Contest Comment Controller")
    @Operation(summary = "공모전 게시글 댓글 조회", description = "사용자가 공모전 게시글의 댓글을 조회할 때 사용하는 API")
    @GetMapping("/comment/{id}")
    public ContestCommentResponseDto readContestComment(@PathVariable Integer id) {
        return contestCommentService.getContestComment(id);
    }

    /**
     * 공모전 게시판 댓글 수정
     */
    @Tag(name = "ContestComment Controller : 공모전 게시글 댓글", description = "Contest Comment Controller")
    @Operation(summary = "공모전 댓글 수정", description = "사용자가 공모전 게시글의 댓글을 수정할 때 사용하는 API")
    @PutMapping("/comment/{id}")
    public ResponseEntity<String> updateContestComment(
            @PathVariable Integer id,
            @RequestBody ContestCommentUpdateRequestDto contestCommentUpdateRequestDto) {
        contestCommentService.updateContestComment(id, contestCommentUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정에 성공했습니다.");
    }

    /**
     * 공모전 게시판 댓글 전체 목록 조회
     */
    @Tag(name = "ContestComment Controller : 공모전 게시글 댓글", description = "Contest Comment Controller")
    @Operation(summary = "공모전 댓글 전체 목록 조회", description = "사용자가 댓글 전체 목록을 조회할 때 사용하는 API")
    @GetMapping("/comment/list/{id}")
    public List<ContestCommentResponseDto> getComments(@PathVariable Integer id) {
        return contestCommentService.getContestComments(id);
    }

}
