package com.example.capstone.controller;



import com.example.capstone.dto.request.contest.ContestPostCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.dto.response.contest.ContestPostDetailResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.service.ContestPostService;
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
import java.io.InvalidObjectException;
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
    public ContestPostDetailResponseDto getContestPostDetail(@RequestHeader("userId") String userId, @PathVariable Integer id){
        return contestPostService.getContestPostDetail(userId,id);
    }


    /**
     * 공모전 게시물 등록
     */
    @PostMapping("/post")
    public ResponseEntity<String> createContestPost(ContestPostCreateRequestDto contestPostCreateRequestDto)throws IOException {
        contestPostService.createContestPost(contestPostCreateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 등록하였습니다.");
    }
    /**
     * 공모전 이미지 조회
     */
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
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deleteContestPost(@PathVariable Integer id){
        contestPostService.deleteContestPost(id);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 삭제하였습니다.");
    }

    /**
     * 공모전 게시물 수정
     */
    @PutMapping("/post/{id}")
    public ResponseEntity<String> updateContestPost(@PathVariable Integer id, ContestPostUpdateRequestDto contestPostUpdateRequestDto)throws IOException{
        contestPostService.updateContestPost(id,contestPostUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("공모전 게시글을 수정하였습니다.");
    }



}
