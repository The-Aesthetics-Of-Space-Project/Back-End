package com.example.capstone.service;


import com.example.capstone.dto.request.contest.ContestLikeRequestDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.entity.community.contest.article.Contest;
import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.community.contest.article.ContestLikeId;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.ContestLikeRepository;
import com.example.capstone.repository.ContestPostRepository;
import com.example.capstone.repository.ContestRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ContestLikeTest {
    @Mock
    private ContestPostRepository contestPostRepository;

    @InjectMocks
    private ContestLikeService contestLikeService;

    @Mock
    private ContestLikeRepository contestLikeRepository;

    @Mock
    private UserRepository userRepository;





    @Test
    @DisplayName("공모전 게시판 좋아요 등록 테스트")
    public void likeContestPostTest(){

        // given

        ContestLikeRequestDto contestLikeRequestDto = mock(ContestLikeRequestDto.class);
        User user = mock(User.class);
        ContestPost contestPost = mock(ContestPost.class);

        when(userRepository.findByUserId(contestLikeRequestDto.getUserId())).thenReturn(Optional.of(user));
        when(contestPostRepository.findById(contestLikeRequestDto.getArticleId())).thenReturn(Optional.of(contestPost));

        // when
        contestLikeService.likeContestPost(contestLikeRequestDto);

        // then
        verify(contestLikeRepository).save(contestLikeRequestDto.toEntity(user,contestPost));

    }






    @Test
    @DisplayName("공모전 게시판 좋아요 취소 테스트")
    public void unLikeContestPostTest(){

        // given

        ContestLikeRequestDto contestLikeRequestDto = mock(ContestLikeRequestDto.class);
        User user = mock(User.class);
        ContestPost contestPost = mock(ContestPost.class);
        ContestLike contestLike = mock(ContestLike.class);

        when(userRepository.findById(contestLikeRequestDto.getUserId())).thenReturn(Optional.of(user));
        when(contestPostRepository.findById(contestLikeRequestDto.getArticleId())).thenReturn(Optional.of(contestPost));
        when(contestLikeRepository.findById(any(ContestLikeId.class))).thenReturn(Optional.of(contestLike));

        // when
        contestLikeService.unlikeContestPost(contestLikeRequestDto);

        // then

        verify(contestLikeRepository).deleteById(any(ContestLikeId.class));
    }
}
