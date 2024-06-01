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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GeneralCommentServiceTest {
    @InjectMocks
    private GeneralCommentService generalCommentService;

    @Mock
    private GeneralPostRepository generalPostRepository;

    @Mock
    private GeneralCommentRepository generalCommentRepository;

    @Mock
    private UserRepository userRepository;



    @Test
    @DisplayName("일반 게시글 댓글 등록 테스트")
    public void createCommentTest(){
        //given
        GeneralCommentCreateRequestDto generalCommentCreateRequestDto = mock(GeneralCommentCreateRequestDto.class);

        GeneralPost generalPost = mock(GeneralPost.class);
        User user = mock(User.class);
        GeneralComment generalComment = mock(GeneralComment.class);

        // stubbing
        when(userRepository.findByNickname(generalCommentCreateRequestDto.getNickname())).thenReturn(Optional.of(user));
        when(generalPostRepository.findByArticleId(generalCommentCreateRequestDto.getArticleId())).thenReturn(Optional.of(generalPost));
        when(generalCommentCreateRequestDto.toEntity(any(User.class), any(GeneralPost.class))).thenReturn(generalComment);

        //when
        generalCommentService.createComment(generalCommentCreateRequestDto);

        //then
        verify(generalCommentRepository).save(generalCommentCreateRequestDto.toEntity(user,generalPost));

    }

    @Test
    @DisplayName("일반 게시글 댓글 삭제 테스트")
    public void deleteCommentTest(){
        // given
        Integer commentId = 1;

        GeneralComment generalComment = mock(GeneralComment.class);

        // stubbing
        when(generalCommentRepository.findById(commentId)).thenReturn(Optional.of(generalComment));

        // when
        generalCommentService.deleteComment(commentId);

        // then
        verify(generalCommentRepository).deleteById(commentId);
    }


    @Test
    @DisplayName("일반 게시글 댓글 조회 테스트")
    public void getCommentTest(){
        //given
        Integer commentId = 1;

        GeneralComment generalComment = mock(GeneralComment.class);
        GeneralPost generalPost = mock(GeneralPost.class);
        User user = mock(User.class);

        when(generalComment.getGeneralPost()).thenReturn(generalPost);
        when(generalComment.getUser()).thenReturn(user);
        when(generalCommentRepository.findById(commentId)).thenReturn(Optional.of(generalComment));

        //when
        GeneralCommentReadResponseDto generalCommentReadResponseDto = GeneralCommentReadResponseDto.toDto(generalComment);
        GeneralCommentReadResponseDto checkDto = generalCommentService.readComment(commentId);

        //then
        verify(generalCommentRepository).findById(commentId);
        assertEquals(generalCommentReadResponseDto,checkDto);

    }


    @Test
    @DisplayName("일반 게시글 댓글 수정 테스트")
    public void updateCommentTest(){
        //given

        Integer commentId = 1;
        GeneralCommentUpdateRequestDto generalCommentUpdateRequestDto = mock(GeneralCommentUpdateRequestDto.class);

        GeneralComment generalComment = mock(GeneralComment.class);

        //stubbing
        when(generalCommentRepository.findById(commentId)).thenReturn(Optional.of(generalComment));

        //when
        generalCommentService.updateComment(commentId,generalCommentUpdateRequestDto);

        //then
        verify(generalCommentRepository).findById(commentId);
        verify(generalComment).updateComment(generalCommentUpdateRequestDto);

    }


    @Test
    @DisplayName("일반 게시글 댓글전체 조회 테스트")
    public void getCommentsTest(){
        //given
        Integer articleId = 1;

        GeneralComment generalComment = mock(GeneralComment.class);
        GeneralPost generalPost = mock(GeneralPost.class);
        User user = mock(User.class);
        List<GeneralComment> posts = new ArrayList<>();

        List<GeneralCommentReadResponseDto> generalCommentReadResponseDtos = new ArrayList<>();

        when(generalComment.getGeneralPost()).thenReturn(generalPost);
        when(generalComment.getUser()).thenReturn(user);
        when(generalCommentRepository.findByGeneralPostArticleIdOrderByCommentIdDesc(articleId)).thenReturn(posts);

        //when
        posts.add(generalComment);
        generalCommentReadResponseDtos.add(GeneralCommentReadResponseDto.toDto(generalComment));
        List<GeneralCommentReadResponseDto> checkDtos = generalCommentService.getComments(articleId);

        //then
        assertEquals(generalCommentReadResponseDtos,checkDtos);

    }

}
