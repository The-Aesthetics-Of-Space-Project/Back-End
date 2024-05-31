package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralCommentCreateRequestDto;
import com.example.capstone.dto.request.GeneralCommentUpdateRequestDto;
import com.example.capstone.dto.request.contest.ContestCommentCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestCommentUpdateRequestDto;
import com.example.capstone.dto.response.GeneralCommentReadResponseDto;
import com.example.capstone.dto.response.contest.ContestCommentResponseDto;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.comment.GeneralComment;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContestCommentService {

    @Autowired
    private ContestCommentRepository contestCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContestPostRepository contestPostRepository;

    /**
     * 공모전 게시판 댓글 등록
     */
    @Transactional
    public void createContestComment(ContestCommentCreateRequestDto contestCommentCreateRequestDto) {
        ContestPost contestPost = contestPostRepository.findById(contestCommentCreateRequestDto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        User user = userRepository.findByNickname(contestCommentCreateRequestDto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        contestCommentRepository.save(contestCommentCreateRequestDto.toEntity(user, contestPost));
    }

    /**
     * 공모전 게시판 댓글 삭제
     */
    @Transactional
    public void deleteContestComment(Integer id) {
        contestCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        contestCommentRepository.deleteById(id);
    }

    /**
     * 공모전 게시판 댓글 조회
     */
    @Transactional
    public ContestCommentResponseDto getContestComment(Integer id) {
        return contestCommentRepository.findById(id)
                .map(ContestCommentResponseDto::createDto)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    /**
     * 공모전 게시판 댓글 수정
     */
    @Transactional
    public void updateContestComment(Integer id, ContestCommentUpdateRequestDto contestCommentUpdateRequestDto) {
        ContestComment contestComment = contestCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        contestComment.updateComment(contestCommentUpdateRequestDto.getContents());
    }


    /**
     * 공모전 게시판 댓글 전체 목록 조회
     */
    @Transactional
    public List<ContestCommentResponseDto> getContestComments(Integer id) {
        return contestCommentRepository.findByContestPost_ArticleId(id)
                .stream()
                .map(ContestCommentResponseDto::createDto)
                .toList();
    }


}
