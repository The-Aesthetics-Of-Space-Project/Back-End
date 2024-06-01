package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralLikeRequestDto;
import com.example.capstone.dto.request.contest.ContestLikeRequestDto;
import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.community.contest.article.ContestLikeId;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.GeneralLikeId;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralLikeRepository;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GeneralLikeServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GeneralLikeService generalLikeService;

    @Mock
    private GeneralPostRepository generalPostRepository;

    @Mock
    private GeneralLikeRepository generalLikeRepository;





    @Test
    @DisplayName("일반 게시판 좋아요 등록 테스트")
    public void likePostTest(){

        // given

        GeneralLikeRequestDto generalLikeRequestDto = mock(GeneralLikeRequestDto.class);

        User user = mock(User.class);
        GeneralPost generalPost = mock(GeneralPost.class);

        // stubbing
        when(userRepository.findByUserId(generalLikeRequestDto.getUserId())).thenReturn(Optional.of(user));
        when(generalPostRepository.findByArticleId(generalLikeRequestDto.getArticleId())).thenReturn(Optional.of(generalPost));

        // when
        generalLikeService.likePost(generalLikeRequestDto);

        // then
        verify(generalLikeRepository).save(generalLikeRequestDto.toEntity(user,generalPost));

    }

    @Test
    @DisplayName("일반 게시판 좋아요 취소 테스트")
    public void unLikePostTest(){

        // given

        GeneralLikeRequestDto generalLikeRequestDto = mock(GeneralLikeRequestDto.class);

        User user = mock(User.class);
        GeneralPost generalPost= mock(GeneralPost.class);
        GeneralLike generalLike = mock(GeneralLike.class);

        when(userRepository.findById(generalLikeRequestDto.getUserId())).thenReturn(Optional.of(user));
        when(generalPostRepository.findById(generalLikeRequestDto.getArticleId())).thenReturn(Optional.of(generalPost));
        when(generalLikeRepository.findById(any(GeneralLikeId.class))).thenReturn(Optional.of(generalLike));

        // when
        generalLikeService.unlikePost(generalLikeRequestDto);

        // then
        verify(generalLikeRepository).deleteById(any(GeneralLikeId.class));
    }
}
