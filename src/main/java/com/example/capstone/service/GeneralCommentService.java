package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralCommentCreateRequestDto;
import com.example.capstone.dto.request.GeneralCommentUpdateRequestDto;
import com.example.capstone.dto.response.GeneralCommentReadResponseDto;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.comment.GeneralComment;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralCommentRepository;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralCommentService {
    private final GeneralCommentRepository generalCommentRepository;
    private final UserRepository userRepository;
    private final GeneralPostRepository generalPostRepository;

    /**
     * 일반 게시판 댓글 등록
     */
    @Transactional
    public void createComment(GeneralCommentCreateRequestDto generalCommentCreateRequestDto) {
        GeneralPost generalPost = generalPostRepository.findByArticleId(generalCommentCreateRequestDto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        User user = userRepository.findByNickname(generalCommentCreateRequestDto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        generalCommentRepository.save(generalCommentCreateRequestDto.toEntity(user, generalPost));
    }

    /**
     * 일반 게시판 댓글 삭제
     */
    @Transactional
    public void deleteComment(Integer id) {
        generalCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // 대댓글 조회
        List<GeneralComment> childComments = generalCommentRepository.findByParentId(id);

        // 대댓글 삭제
        for(GeneralComment childComment : childComments) {
            generalCommentRepository.deleteById(childComment.getCommentId());
        }

        // 원댓글 삭제
        generalCommentRepository.deleteById(id);
    }

    /**
     * 일반 게시판 댓글 조회
     */
    @Transactional
    public GeneralCommentReadResponseDto readComment(Integer id) {
        return generalCommentRepository.findById(id)
                .map(GeneralCommentReadResponseDto::toDto)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    /**
     * 일반 게시판 댓글 수정
     */
    @Transactional
    public void updateComment(Integer id, GeneralCommentUpdateRequestDto generalCommentUpdateRequestDto) {
        GeneralComment generalComment = generalCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        generalComment.updateComment(generalCommentUpdateRequestDto);
    }

    /**
     * 일반 게시판 댓글 전체 목록 조회
     */
    @Transactional
    public List<GeneralCommentReadResponseDto> getComments(Integer id) {
        return generalCommentRepository.findByGeneralPostArticleIdOrderByCommentIdDesc(id)
                .stream()
                .map(GeneralCommentReadResponseDto::toDto)
                .toList();
    }
}
