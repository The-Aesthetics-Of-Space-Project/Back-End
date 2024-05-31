package com.example.capstone.controller;



import com.example.capstone.dto.request.contest.ContestPostCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostDetailRequestDto;
import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.dto.response.contest.ContestPostDetailResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.service.ContestPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    @Tag(name = "ContestPost Controller : 공모전 게시글 관리", description = "Contest Post Controller")
    @Operation(summary = "공모전 게시물 전체 목록 조회", description = "사용자가 공모전 게시물 전체 목록을 조회할 때 사용하는 API")
    @GetMapping("/posts")
    public List<ContestPostsResponseDto> getContestPosts(){
        return contestPostService.getContestPosts();
    }


    /**
     * 공모전 게시물 상세 조회
     */

    @Tag(name = "ContestPost Controller : 공모전 게시글 관리", description = "Contest Post Controller")
    @Operation(summary = "공모전 게시물 상세 조회", description = "사용자가 공모전 게시물을 조회할 때 사용하는 API")
    @PostMapping("/post/{id}")
    public ContestPostDetailResponseDto getContestPostDetail(@RequestBody ContestPostDetailRequestDto contestPostDetailRequestDto, @PathVariable Integer id){
        return contestPostService.getContestPostDetail(contestPostDetailRequestDto,id);
    }


    /**
     * 공모전 게시물 등록
     */
    @Tag(name = "ContestPost Controller : 공모전 게시글 관리", description = "Contest Post Controller")
    @Operation(summary = "공모전 게시물 등록", description = "사용자가 공모전 게시물을 등록할 때 사용하는 API")
    @PostMapping("/post")
    public ResponseEntity<String> createContestPost(ContestPostCreateRequestDto contestPostCreateRequestDto)throws IOException {
        contestPostService.createContestPost(contestPostCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 등록하였습니다.");
    }

    /**
     * 공모전 이미지 조회
     */
    @Tag(name = "ContestPost Controller : 공모전 게시글 관리", description = "Contest Post Controller")
    @Operation(summary = "공모전 게시글의 썸네일 사진을 조회", description = "공모전 게시글의 썸네일 사진을 조회할 때 사용하는 API")
    @GetMapping(value = "/image/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getContestPostImage(@PathVariable Integer id)throws IOException{
        log.info("공모전 게시글 섬네일 조회 :"+id);
        InputStream is = new FileInputStream("C:/temp/ContestPostImage/"+id+".jpg");
        byte[] image = IOUtils.toByteArray(is);
        is.close();
        return image;
    }


    /**
     * 공모전 게시물 삭제
     */
    @Tag(name = "ContestPost Controller : 공모전 게시글 관리", description = "Contest Post Controller")
    @Operation(summary = "공모전 게시물 삭제", description = "사용자가 공모전 게시물을 삭제할 때 사용하는 API")
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deleteContestPost(@PathVariable Integer id){
        contestPostService.deleteContestPost(id);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 삭제하였습니다.");
    }

    /**
     * 공모전 게시물 수정
     */
    @Tag(name = "ContestPost Controller : 공모전 게시글 관리", description = "Contest Post Controller")
    @Operation(summary = "공모전 게시물 수정", description = "사용자가 공모전 게시물을 수정할 때 사용하는 API")
    @PutMapping("/post/{id}")
    public ResponseEntity<String> updateContestPost(@PathVariable Integer id, @RequestBody ContestPostUpdateRequestDto contestPostUpdateRequestDto)throws IOException{
        contestPostService.updateContestPost(id,contestPostUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 수정하였습니다.");
    }



}
