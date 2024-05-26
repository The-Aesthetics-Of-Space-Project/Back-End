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

    @PostMapping("/comment")
    public ResponseEntity<String> createContestComment(@RequestBody ContestCommentCreateRequestDto contestCommentCreateRequestDto) {
        contestCommentService.createContestComment(contestCommentCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 등록에 성공했습니다.");
    }

    /**
     * 공모전 게시판 댓글 삭제
     */

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteContestComment(@PathVariable Integer id) {
        contestCommentService.deleteContestComment(id);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제에 성공했습니다.");
    }

    /**
     * 공모전 게시판 댓글 조회
     */

    @GetMapping("/comment/{id}")
    public ContestCommentResponseDto readContestComment(@PathVariable Integer id) {
        return contestCommentService.getContestComment(id);
    }

    /**
     * 공모전 게시판 댓글 수정
     */

    @PutMapping("/comment/{id}")
    public ResponseEntity<String> updateContestComment(
            @PathVariable Integer id,
            @RequestBody ContestCommentUpdateRequestDto contestCommentUpdateRequestDto
    ) {
        contestCommentService.updateContestComment(id, contestCommentUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정에 성공했습니다.");
    }

    /**
     * 공모전 게시판 댓글 전체 목록 조회
     */
    @GetMapping("/comment/list/{id}")
    public List<ContestCommentResponseDto> getComments(@PathVariable Integer id) {
        return contestCommentService.getContestComments(id);
    }


}
