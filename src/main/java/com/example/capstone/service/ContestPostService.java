package com.example.capstone.service;


import com.example.capstone.dto.request.contest.ContestPostCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.contest.ContestPostDetailResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.ContestPostRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContestPostService {
    @Autowired
    private ContestPostRepository contestPostRepository;

    @Autowired
    private UserRepository userRepository;

    private final Path root = Paths.get("c:\\Temp\\image\\ContestPostImage");


    /**
     * 일반 게시물 전체 목록 조회
     */
    @Transactional
    public List<ContestPostsResponseDto> getContestPosts() {
        return contestPostRepository.findAllByOrderByContestIdDesc()
                .stream()
                .map(ContestPostsResponseDto::createDto)
                .toList();
    }

    /**
     * 일반 게시물 상세 조회
     */
    @Transactional
    public ContestPostDetailResponseDto getContestPostDetail(Integer id){
        return contestPostRepository.findById(id)
                .map(ContestPostDetailResponseDto::createDto)
                .orElseThrow(()-> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }

    /**
     * 일반 게시물 등록
     */
    @Transactional
    public void createContestPost(ContestPostCreateRequestDto contestPostCreateRequestDto) throws IOException {
        if(!Files.exists(root)) {
            Files.createDirectories(root);
        }
        User user = userRepository.findByNickname(contestPostCreateRequestDto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        String fileExt = contestPostCreateRequestDto
                .getThumbnail()
                .getContentType()
                .split("/")[1]; // 확장명 추출 image/png -> ["image", "png"]

        // DB에 저장
        ContestPost contestPost = contestPostRepository.save(contestPostCreateRequestDto.toEntity(user));
        contestPost.setThumbnail("/api/contest/image/"+contestPost.getContestId().toString());

        if( contestPostCreateRequestDto.getThumbnail()!=null ) {
            File saveFile = new File("/ContestPostImage/"+contestPost.getContestId() + ".jpg");
            contestPostCreateRequestDto
                    .getThumbnail()
                    .transferTo(saveFile);
        }
    }

    /**
     * 일반 게시물 삭제
     */
    @Transactional
    public void deleteContestPost(Integer id){
        contestPostRepository.deleteById(id);
    }

    /**
     * 일반 게시물 수정
     */

    @Transactional
    public void updateContestPost(Integer id, ContestPostUpdateRequestDto contestPostUpdateRequestDto){
        contestPostRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시물이 존재하지 않습니다."))
                .updateContestPost(contestPostUpdateRequestDto);
    }




}
