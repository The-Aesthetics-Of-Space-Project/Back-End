package com.example.capstone.service;

import com.example.capstone.dto.request.contest.ContestCommentCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestCommentUpdateRequestDto;
import com.example.capstone.dto.response.contest.ContestCommentResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.contest.comment.ContestComment;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.ContestCommentRepository;
import com.example.capstone.repository.ContestLikeRepository;
import com.example.capstone.repository.ContestPostRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ContestCommentTest {


    @InjectMocks
    private ContestCommentService contestCommentService;

    @Mock
    private ContestCommentRepository contestCommentRepository;

    @Mock
    private ContestPostRepository contestPostRepository;

    @Mock
    private UserRepository userRepository;





    @Test
    @DisplayName("공모전 게시글 댓글 등록 테스트")
    public void createContestCommentTest(){
        //given
        ContestCommentCreateRequestDto contestCommentCreateRequestDto = mock(ContestCommentCreateRequestDto.class);
        ContestPost contestPost = mock(ContestPost.class);
        User user = mock(User.class);
        ContestComment contestComment = mock(ContestComment.class);


        when(userRepository.findByNickname(contestCommentCreateRequestDto.getNickname())).thenReturn(Optional.of(user));
        when(contestPostRepository.findById(contestCommentCreateRequestDto.getArticleId())).thenReturn(Optional.of(contestPost));
        when(contestCommentCreateRequestDto.toEntity(any(User.class), any(ContestPost.class))).thenReturn(contestComment);


        //when
        contestCommentService.createContestComment(contestCommentCreateRequestDto);


        //then
        verify(contestCommentRepository).save(any(ContestComment.class));

    }

    @Test
    @DisplayName("공모전 게시글 댓글 삭제 테스트")
    public void deleteContestCommentTest(){
        //given
        Integer commentId = 1;

        ContestComment contestComment = mock(ContestComment.class);

        when(contestCommentRepository.findById(commentId)).thenReturn(Optional.of(contestComment));


        //when
        contestCommentService.deleteContestComment(commentId);


        //then
        verify(contestCommentRepository).deleteById(commentId);

    }



    @Test
    @DisplayName("공모전 게시글 댓글 조회 테스트")
    public void getContestCommentTest(){
        //given
        Integer commentId = 1;

        ContestComment contestComment = mock(ContestComment.class);
        ContestPost contestPost = mock(ContestPost.class);
        User user = mock(User.class);

        when(contestComment.getContestPost()).thenReturn(contestPost);
        when(contestComment.getUser()).thenReturn(user);
        when(contestCommentRepository.findById(commentId)).thenReturn(Optional.of(contestComment));


        //when
        ContestCommentResponseDto contestCommentResponseDto = ContestCommentResponseDto.createDto(contestComment);
        ContestCommentResponseDto checkDto = contestCommentService.getContestComment(commentId);


        //then
        verify(contestCommentRepository).findById(commentId);
        assertEquals(contestCommentResponseDto,checkDto);

    }


    @Test
    @DisplayName("공모전 게시글 댓글 수정 테스트")
    public void updateContestCommentTest(){
        //given

        Integer commentId = 1;
        ContestCommentUpdateRequestDto contestCommentUpdateRequestDto = mock(ContestCommentUpdateRequestDto.class);

        ContestComment contestComment = mock(ContestComment.class);
        when(contestCommentRepository.findById(commentId)).thenReturn(Optional.of(contestComment));


        //when
        contestCommentService.updateContestComment(commentId,contestCommentUpdateRequestDto);


        //then
        verify(contestCommentRepository).findById(commentId);
        verify(contestComment).updateComment(contestCommentUpdateRequestDto.getContents());

    }



    @Test
    @DisplayName("공모전 게시글 댓글전체 조회 테스트")
    public void getContestCommentsTest(){
        //given
        Integer articleId = 1;

        ContestComment contestComment = mock(ContestComment.class);
        ContestPost contestPost = mock(ContestPost.class);
        User user = mock(User.class);
        List<ContestComment> posts = new ArrayList<>();
        List<ContestCommentResponseDto> contestCommentResponseDtos = new ArrayList<>();


        when(contestComment.getContestPost()).thenReturn(contestPost);
        when(contestComment.getUser()).thenReturn(user);
        when(contestCommentRepository.findByContestPost_ArticleId(articleId)).thenReturn(posts);


        //when
        posts.add(contestComment);
        contestCommentResponseDtos.add(ContestCommentResponseDto.createDto(contestComment));
        List<ContestCommentResponseDto> checkDtos = contestCommentService.getContestComments(articleId);


        //then
        assertEquals(contestCommentResponseDtos,checkDtos);

    }
}
