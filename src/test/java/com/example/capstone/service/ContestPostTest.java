package com.example.capstone.service;


import com.example.capstone.dto.request.contest.ContestPostCreateRequestDto;
import com.example.capstone.dto.request.contest.ContestPostDetailRequestDto;
import com.example.capstone.dto.request.contest.ContestPostUpdateRequestDto;
import com.example.capstone.dto.response.contest.ContestPostDetailResponseDto;
import com.example.capstone.dto.response.contest.ContestPostsResponseDto;
import com.example.capstone.dto.response.contest.UserContestPostsResponseDto;
import com.example.capstone.entity.community.contest.article.Contest;
import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.community.contest.article.ContestLikeId;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.ContestLikeRepository;
import com.example.capstone.repository.ContestPostRepository;
import com.example.capstone.repository.ContestRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.IntegerAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ContestPostTest {

    @Mock
    private ContestPostRepository contestPostRepository;

    @InjectMocks
    private ContestPostService contestPostService;

    @Mock
    private ContestLikeRepository contestLikeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContestRepository contestRepository;




    @Test
    @DisplayName("공모전 게시물 전체 목록 조회 테스트")
    public void getContestPostsTest(){

        // given


        ContestPost contestPost = mock(ContestPost.class);
        User user = mock(User.class);
        Contest contest = mock(Contest.class);
        List<ContestPost> posts = new ArrayList<>();
        List<ContestPostsResponseDto> contestPostsResponseDtos = new ArrayList<>();


                               // 레포지토리 조회 시 posts 리스트 리턴

        when(contestPost.getUser()).thenReturn(user);       // contestPost.getUser() 시 user 객체 리턴
        when(contestPost.getContest()).thenReturn(contest); // contestPost.getContest() 시 contest 객체 리턴
        when(contestPostRepository.findAllByOrderByArticleIdDesc()).thenReturn(posts);



        // when
        posts.add(contestPost);
        contestPostsResponseDtos.add(ContestPostsResponseDto.createDto(contestPost));
        List<ContestPostsResponseDto> checkDtos = contestPostService.getContestPosts();

        // then
        assertEquals(contestPostsResponseDtos,checkDtos);

    }



    @Test
    @DisplayName("공모전 게시물 상세 조회 테스트")
    public void getContestPostDetailTest(){

        // given
        String userId = "user1";
        Integer articleId = 1;

        boolean isLike = false;

        ContestPostDetailRequestDto contestPostDetailRequestDto = mock(ContestPostDetailRequestDto.class);

        ContestPost contestPost = mock(ContestPost.class);
        User user = mock(User.class);
        Contest contest = mock(Contest.class);

        //stubbing
        when(contestPostRepository.findById(articleId)).thenReturn(Optional.of(contestPost));
        when(contestPost.getUser()).thenReturn(user);       // contestPost.getUser() 시 user 객체 리턴
        when(contestPost.getContest()).thenReturn(contest); // contestPost.getContest() 시 contest 객체 리턴



        // when
        ContestPostDetailResponseDto contestPostDetailResponseDto = ContestPostDetailResponseDto.createDto(contestPost);
        contestPostDetailResponseDto.setLike(isLike);
        ContestPostDetailResponseDto checkDto = contestPostService.getContestPostDetail(contestPostDetailRequestDto,articleId);

        // then
        assertEquals(contestPostDetailResponseDto,checkDto);

    }



    @Test
    @DisplayName("공모전 게시물 등록 테스트")
    public void createContestPostTest()throws IOException{

        // given

        ContestPostCreateRequestDto contestPostCreateRequestDto = mock(ContestPostCreateRequestDto.class);

        User user = mock(User.class);
        ContestPost contestPost = mock(ContestPost.class);
        Contest contest = mock(Contest.class);

        // stubbing
        when(userRepository.findByNickname(contestPostCreateRequestDto.getNickname())).thenReturn(Optional.of(user));
        when(contestPostRepository.save(contestPostCreateRequestDto.toEntity(user))).thenReturn(contestPost);
        when(contestRepository.findById(contestPostCreateRequestDto.getContestId())).thenReturn(Optional.of(contest));

        // when
        contestPostService.createContestPost(contestPostCreateRequestDto);

        // then
        //contestPost의 setContest라는 메서드가 호출되었는지 확인
        verify(contestPost).setContest(contestRepository.findById(contestPostCreateRequestDto.getContestId()).get());

    }

    @Test
    @DisplayName("공모전 게시물 수정 테스트")
    public void updateContestPostTest()throws IOException{

        // given

        ContestPostUpdateRequestDto contestPostUpdateRequestDto = mock(ContestPostUpdateRequestDto.class);

        Integer articleId = 1;

        User user = mock(User.class);
        ContestPost contestPost = mock(ContestPost.class);
        Contest contest = mock(Contest.class);

        // createContestPost 메서드 동작에 필요한 반환값들 지정
        when(contestPostRepository.findById(articleId)).thenReturn(Optional.of(contestPost));

        // when
        contestPostService.updateContestPost(articleId,contestPostUpdateRequestDto);

        // then
        verify(contestPost).updateContestPost(contestPostUpdateRequestDto);
        verify(contestPostUpdateRequestDto).getThumbnail();

    }





}
