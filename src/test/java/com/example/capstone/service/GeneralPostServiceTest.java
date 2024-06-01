package com.example.capstone.service;


import com.example.capstone.dto.request.GeneralPostCreateRequestDto;
import com.example.capstone.dto.request.GeneralPostUpdateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostDetailRequestDto;
import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.dto.response.GeneralPostDetailResponseDto;
import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.contest.ContestPostDetailResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.entity.community.contest.article.Contest;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.general.article.*;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
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
public class GeneralPostServiceTest {

    @InjectMocks
    private GeneralPostService generalPostService;

    @Mock
    private GeneralPostRepository generalPostRepository;

    @Mock
    private GeneralLikeRepository generalLikeRepository;

    @Mock
    private ScrapRepository scrapRepository;

    @Mock
    private UserRepository userRepository;
    @Test
    @DisplayName("일반 게시물 전체 목록 조회 테스트")
    public void getPostsTest(){

        // given
        GeneralPost generalPost = mock(GeneralPost.class);
        User user = mock(User.class);
        List<GeneralPost> posts = new ArrayList<>();
        List<GeneralPostListResponseDto> generalPostListResponseDtos = new ArrayList<>();

        //stubbing
        when(generalPost.getUser()).thenReturn(user);       // contestPost.getUser() 시 user 객체 리턴
        when(generalPostRepository.findAllByOrderByArticleIdDesc()).thenReturn(posts);

        // when
        posts.add(generalPost);
        generalPostListResponseDtos.add(GeneralPostListResponseDto.createDto(generalPost));
        List<GeneralPostListResponseDto> checkDtos = generalPostService.getPosts();

        // then
        assertEquals(generalPostListResponseDtos,checkDtos);

    }

    @Test
    @DisplayName("일반 게시물 상세 조회 테스트")
    public void getPostDetailTest(){

        // given
        String userId = "user1";
        Integer articleId = 1;

        GeneralPost generalPost = mock(GeneralPost.class);
        User user = mock(User.class);
        ScrapId scrapId = mock(ScrapId.class);
        GeneralLikeId generalLikeId = mock(GeneralLikeId.class);

        //stubbing
        when(generalPost.getUser()).thenReturn(user);       // contestPost.getUser() 시 user 객체 리턴
        when(generalPostRepository.findById(articleId)).thenReturn(Optional.of(generalPost));
        when(generalLikeRepository.existsById(any(GeneralLikeId.class))).thenReturn(true);
        when(scrapRepository.existsById(any(ScrapId.class))).thenReturn(true);

        // when
        GeneralPostDetailResponseDto generalPostDetailResponseDto = GeneralPostDetailResponseDto.createDto(generalPost);
        generalPostDetailResponseDto.setIsLiked(generalLikeRepository.existsById(generalLikeId));
        generalPostDetailResponseDto.setIsScraped(scrapRepository.existsById(scrapId));
        GeneralPostDetailResponseDto checkDto = generalPostService.getPost(userId,articleId);

        // then
        assertEquals(generalPostDetailResponseDto,checkDto);

    }
    @Test
    @DisplayName("일반 게시물 등록 테스트")
    public void createPostTest() {

        // given

        GeneralPostCreateRequestDto generalPostCreateRequestDto = mock(GeneralPostCreateRequestDto.class);

        User user = mock(User.class);
        GeneralPost generalPost = mock(GeneralPost.class);

        // stubbing
        when(userRepository.findByNickname(generalPostCreateRequestDto.getNickname())).thenReturn(Optional.of(user));
        when(generalPostCreateRequestDto.toEntity(user)).thenReturn(generalPost);

        // when
        generalPostService.createPost(generalPostCreateRequestDto);

        // then
        verify(generalPostRepository).save(generalPost);

    }

    @Test
    @DisplayName("일반 게시물 수정 테스트")
    public void updatePostTest(){

        // given

        GeneralPostUpdateRequestDto generalPostUpdateRequestDto = mock(GeneralPostUpdateRequestDto.class);
        Integer articleId = 1;

        GeneralPost generalPost = mock(GeneralPost.class);

        // stubbing
        when(generalPostRepository.findById(articleId)).thenReturn(Optional.of(generalPost));

        // when
        generalPostService.updatePost(articleId,generalPostUpdateRequestDto);

        // then
        verify(generalPost).updatePost(generalPostUpdateRequestDto);

    }


    @Test
    @DisplayName("공모전 게시물 수정 테스트")
    public void getPopularPostsTest(){

        // given
        Pageable pageable = PageRequest.of(0,3);
        User user = mock(User.class);
        GeneralPost generalPost = mock(GeneralPost.class);
        List<GeneralPost> posts = new ArrayList<>();
        List<GeneralPostListResponseDto> generalPostListResponseDtos ;

        // stubbing
        when(generalPostRepository.findTop3ByLikes(pageable)).thenReturn(posts);
        when(generalPost.getUser()).thenReturn(user);

        // when
        posts.add(generalPost);
        posts.add(generalPost);
        posts.add(generalPost);
        generalPostListResponseDtos = posts.stream().map(GeneralPostListResponseDto::createDto).toList();
        List<GeneralPostListResponseDto> checkDtos = generalPostService.getPopularPosts();

        // then
        assertEquals(generalPostListResponseDtos,checkDtos);

    }
}
