package com.example.capstone.service;


import com.example.capstone.dto.request.contest.ContestPostCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.dto.response.contest.ContestPostDetailResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.entity.community.contest.article.ContestLikeId;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.ContestLikeRepository;
import com.example.capstone.repository.ContestPostRepository;
import com.example.capstone.repository.ContestRepository;
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
    private ContestRepository contestRepository;

    @Autowired
    private ContestLikeRepository contestLikeRepository;

    @Autowired
    private UserRepository userRepository;

    private final Path root = Paths.get("c:\\Temp\\ContestPostImage");


    /**
     * 공모전 게시물 전체 목록 조회
     */
    @Transactional
    public List<ContestPostsResponseDto> getContestPosts() {
        return contestPostRepository.findAllByOrderByArticleIdDesc()
                .stream()
                .map(ContestPostsResponseDto::createDto)
                .toList();
    }

    /**
     * 공모전 게시물 상세 조회
     */
    @Transactional
    public ContestPostDetailResponseDto getContestPostDetail(String userId, Integer id){
        ContestLikeId contestLikeIdid = new ContestLikeId(userId, id);
        boolean isLike = !contestLikeRepository.findById(contestLikeIdid).isEmpty();

        ContestPostDetailResponseDto contestPostDetailResponseDto = contestPostRepository.findById(id)
                .map(ContestPostDetailResponseDto::createDto)
                .orElseThrow(()-> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        contestPostDetailResponseDto.setLike(isLike);
        return contestPostDetailResponseDto;
    }

    /**
     * 공모전 게시물 등록
     */
    @Transactional
    public void createContestPost(ContestPostCreateRequestDto contestPostCreateRequestDto) throws IOException {
        if(!Files.exists(root)) {
            Files.createDirectories(root);
        }



        User user = userRepository.findByNickname(contestPostCreateRequestDto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

//        String fileExt = contestPostCreateRequestDto
//                .getThumbnail()
//                .getContentType()
//                .split("/")[1]; // 확장명 추출 image/png -> ["image", "png"]

        // DB에 저장
        ContestPost contestPost = contestPostRepository.save(contestPostCreateRequestDto.toEntity(user));



        // 공모전 게시글 공모전 아이디, 섬네일 저장
        contestPost.setContest(contestRepository.findById(contestPostCreateRequestDto.getContestId()).get());
        contestPost.setThumbnail("/api/contest/image/"+contestPost.getArticleId().toString());

        if( contestPostCreateRequestDto.getThumbnail()!=null ) {
            File saveFile = new File(root.toString()+"/"+contestPost.getArticleId() +".jpg");
            contestPostCreateRequestDto
                    .getThumbnail()
                    .transferTo(saveFile);
        }
    }

    /**
     * 공모전 게시물 삭제
     */
    @Transactional
    public void deleteContestPost(Integer id){
        contestPostRepository.deleteById(id);
    }

    /**
     * 공모전 게시물 수정
     */

    @Transactional
    public void updateContestPost(Integer id, ContestPostUpdateRequestDto contestPostUpdateRequestDto)throws IOException{

            ContestPost contestPost = contestPostRepository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("게시물이 존재하지 않습니다."))
                    .updateContestPost(contestPostUpdateRequestDto);

        String fileExt = contestPostUpdateRequestDto
                .getThumbnail()
                .getContentType()
                .split("/")[1]; // 확장명 추출 image/png -> ["image", "png"]


            if( contestPostUpdateRequestDto.getThumbnail()!=null ) {
                File saveFile = new File(root.toString()+"/"+contestPost.getArticleId() +".jpg");
                contestPostUpdateRequestDto
                        .getThumbnail()
                        .transferTo(saveFile);
            }
    }




}
