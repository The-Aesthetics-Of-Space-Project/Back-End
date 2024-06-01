package com.example.capstone.service;


import com.example.capstone.dto.request.UserDetailsUpdateRequestDto;
import com.example.capstone.dto.response.UserDetailsResponseDto;
import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.Scrap;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.FollowRepository;
import com.example.capstone.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private MypageService mypageService;

    @Mock
    private FollowRepository followRepository;

    @Mock
    private UserRepository userRepository;



    @Test
    @DisplayName("유저 정보 조회")
    public void getUserDetailTest() {

        //given

        String userNick = "aaa";

        Set<Scrap> scraps = new HashSet<Scrap>();
        Set<GeneralLike> glikes = new HashSet<>();
        Set<ContestLike> clikes = new HashSet<>();
        Set<Follow> followings = new HashSet<>();
        Set<Follow> followers = new HashSet<>();


        Optional<User> user = Optional.of(
                new User("aaa", "aaa", "aaa", "aaa",
                null,null,glikes,scraps,null,null,clikes,followings,followers));
        UserDetailsResponseDto checkDto = new UserDetailsResponseDto(
                "aaa","aaa","aaa",0,0,0,0);


        // when
        when(userRepository.findByNickname(userNick)).thenReturn(user);
        UserDetailsResponseDto userDetailsResponseDto = userService.getUserDetails(userNick);

        // then
        assertEquals(userDetailsResponseDto,checkDto);

    }

    @Test
    @DisplayName("유저 정보 업데이트")
    public void updateUserDetailsTest() throws IOException {
        // given
        String email = "email@example.com";
        UserDetailsUpdateRequestDto updateRequestDto = new UserDetailsUpdateRequestDto();
        MockMultipartFile mockFile = new MockMultipartFile(
                "profile", "profile.jpg", "image/jpeg", "test image content".getBytes());
        updateRequestDto.setProfile(mockFile);

        User user = mock(User.class);

        // when
        when(userRepository.findByUserId(email)).thenReturn(Optional.of(user));
        userService.updateUserDetails(email, updateRequestDto);

        // then
        verify(userRepository).findByUserId(email);
        verify(user).updateDetails(eq(email), eq(updateRequestDto));

        File savedFile = new File("C:/Temp/profile/" + email + ".jpg");
        assertTrue(savedFile.exists());
        savedFile.delete(); // 테스트 후 파일 삭제
    }







}
