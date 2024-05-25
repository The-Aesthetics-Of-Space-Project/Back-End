package com.example.capstone.controller;

import com.example.capstone.dto.request.GeneralPostCreateRequestDto;
import com.example.capstone.dto.request.GeneralPostUpdateRequestDto;
import com.example.capstone.dto.response.GeneralPostDetailResponseDto;
import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.service.GeneralPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeneralPostController {
    private final GeneralPostService generalPostService;

    /**
     * 일반 게시물 전체 목록 조회
     */
    @Tag(name = "GeneralPost Controller : 일반 게시판 게시물", description = "General Post Controller")
    @Operation(summary = "게시물 전체 목록 조회", description = "사용자가 게시물 전체 목록을 조회할 때 사용하는 API")
    @GetMapping("/api/general/posts")
    public List<GeneralPostListResponseDto> getPosts() {
        return generalPostService.getPosts();
    }

    /**
     * 일반 게시물 상세 조회
     */
    @Tag(name = "GeneralPost Controller : 일반 게시판 게시물", description = "General Post Controller")
    @Operation(summary = "게시물 상세 조회", description = "사용자가 게시물을 조회할 때 사용하는 API")
    @GetMapping("/api/general/post/{id}")
    public GeneralPostDetailResponseDto getPost(@RequestHeader("userId") String userId, @PathVariable Integer id) {

        return generalPostService.getPost(userId, id);
    }

    /**
     * 일반 게시물 등록
     */
    @Tag(name = "GeneralPost Controller : 일반 게시판 게시물", description = "General Post Controller")
    @Operation(summary = "게시물 등록", description = "사용자가 게시물을 등록할 때 사용하는 API")
    @PostMapping("/api/general/post")
    public ResponseEntity<String> createPost(@RequestBody GeneralPostCreateRequestDto generalPostCreateRequestDto) {
        generalPostService.createPost(generalPostCreateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 등록에 성공했습니다.");
    }

    /**
     * 일반 게시물 삭제
     */
    @Tag(name = "GeneralPost Controller : 일반 게시판 게시물", description = "General Post Controller")
    @Operation(summary = "게시물 삭제", description = "사용자가 게시물을 삭제할 때 사용하는 API")
    @DeleteMapping("/api/general/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        generalPostService.deletePost(id);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 삭제에 성공했습니다.");
    }

    /**
     * 일반 게시물 수정
     */
    @Tag(name = "GeneralPost Controller : 일반 게시판 게시물", description = "General Post Controller")
    @Operation(summary = "게시물 수정", description = "사용자가 게시물을 수정할 때 사용하는 API")
    @PutMapping("/api/general/post/{id}")
    public ResponseEntity<String> updatePost(
            @PathVariable Integer id,
            @RequestBody GeneralPostUpdateRequestDto generalPostUpdateRequestDto
    ) {
        generalPostService.updatePost(id, generalPostUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("게시물 수정에 성공했습니다.");
    }

    /**
     * 좋아요 수가 가장 많은 상위 3개의 게시물 목록 조회
     */
    @Tag(name = "GeneralPost Controller : 일반 게시판 게시물", description = "General Post Controller")
    @Operation(summary = "인기 게시물 목록 조회", description = "사용자가 좋아요 수가 가장 많은 상위 3개의 게시물 목록을 조회할 때 사용하는 API")
    @GetMapping("/api/general/posts/popular")
    public List<GeneralPostListResponseDto> getPopularPosts() {
        return generalPostService.getPopularPosts();
    }
}
