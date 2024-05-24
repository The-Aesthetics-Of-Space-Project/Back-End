package com.example.capstone.controller;



import com.example.capstone.dto.request.contest.ContestPostCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.dto.response.contest.ContestPostDetailResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.service.ContestPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contest")
@Slf4j
public class ContestPostController {

    @Autowired
    private ContestPostService contestPostService;

    /**
     * 공모전 게시물 전체 목록 조회
     */
    @GetMapping("/posts")
    public List<ContestPostsResponseDto> getContestPosts(){
        return contestPostService.getContestPosts();
    }

    /**
     * 공모전 게시물 상세 조회
     */
    @GetMapping("/post/{id}")
    public ContestPostDetailResponseDto getContestPostDetail(@PathVariable Integer id){
        return contestPostService.getContestPostDetail(id);
    }


    /**
     * 공모전 게시물 등록
     */
    @PostMapping("/post")
    public ResponseEntity<String> createContestPost(ContestPostCreateRequestDto contestPostCreateRequestDto){
        contestPostService.createContestPost(contestPostCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 등록하였습니다.");
    }


    /**
     * 공모전 게시물 삭제
     */
    @DeleteMapping("/post")
    public ResponseEntity<String> deleteContestPost(@RequestParam Integer id){
        contestPostService.deleteContestPost(id);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 삭제하였습니다.");
    }

    /**
     * 공모전 게시물 수정
     */
    @PutMapping("/post")
    public ResponseEntity<String> updateContestPost(@RequestParam Integer id, ContestPostUpdateRequestDto contestPostUpdateRequestDto){
        contestPostService.updateContestPost(id,contestPostUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 수정하였습니다.");
    }



}
