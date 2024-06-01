package com.example.capstone.service;



import com.example.capstone.dto.response.GeneralPostListResponseDto;
import com.example.capstone.dto.response.UserFollowResponseDto;
import com.example.capstone.dto.response.UserPostsResponseDto;
import com.example.capstone.dto.response.contest.UserContestPostsResponseDto;
import com.example.capstone.entity.community.contest.article.Contest;
import com.example.capstone.entity.community.contest.article.ContestLike;
import com.example.capstone.entity.community.contest.article.ContestPost;
import com.example.capstone.entity.community.general.article.GeneralLike;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.community.general.article.Scrap;
import com.example.capstone.entity.follow.Follow;
import com.example.capstone.entity.follow.FollowId;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class MypageServiceTest {

    @InjectMocks
    private MypageService mypageService;

    @Mock
    private UserRepository userRepository;


    @Mock
    private GeneralPostRepository generalPostRepository;

    @Mock
    private ContestPostRepository contestPostRepository;

    @Mock
    private ScrapRepository scrapRepository;

    @Mock
    private GeneralLikeRepository generalLikeRepository;

    @Mock
    private  ContestLikeRepository contestLikeRepository;

    @Mock
    private FollowRepository followRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("팔로우 요청")
    public void followUserTest() {
        // given
        String userId = "user1";
        String followId = "user2";
        User user = mock(User.class);
        User followed = mock(User.class);

        // stubbing
        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(user));
        when(userRepository.findByUserId(followId)).thenReturn(Optional.of(followed));

        // when
        mypageService.followUser(userId, followId);

        // then
        verify(followRepository).save(any(Follow.class));

        // userRepository의 findByUserId 메소드가 각각의 userId와 followId로 호출되었는지 검증
        verify(userRepository).findByUserId(userId);
        verify(userRepository).findByUserId(followId);
    }
    @Test
    @DisplayName("팔로우 여부 확인")
    public void isFollowTest(){
        // given
        String userNick = "user1"; //팔로우 한사람
        String followedNick = "user2"; //팔로우 당한사람

        Optional<User> user = Optional.of(mock(User.class));
        Optional<User> followed = Optional.of(mock(User.class));
        Optional<Follow> follow = Optional.of(mock(Follow.class));

        FollowId id = new FollowId(
                user.get().getUserId(),         //  팔로우 당한사람
                followed.get().getUserId());    //  팔로우 한사람


        // stubbing
        when(userRepository.findByNickname(userNick)).thenReturn(user);
        when(userRepository.findByNickname(followedNick)).thenReturn(followed);
        when(followRepository.findById(id)).thenReturn(follow);

        // when
        boolean isFollow = mypageService.isFollow(userNick,followedNick);

        // then
        assertTrue(isFollow);

        verify(userRepository).findByNickname(userNick);
        verify(userRepository).findByNickname(followedNick);
        verify(followRepository).findById(id);
    }


    @Test
    @DisplayName("팔로워 목록 조회")
    public void getFollowersTest(){
        // given
        String userId = "user1"; // 팔로워 목록을 조회할 유저 ID

        User user1 = mock(User.class);
        User follower2 = mock(User.class);

        Follow follow1 = new Follow(new FollowId(
                user1.getUserId(),follower2.getUserId()),
                user1,follower2);

        List<Follow> followers = new ArrayList<>();
        followers.add(follow1);

        List<UserFollowResponseDto> userFollowResponseDtos = new ArrayList<>();
        userFollowResponseDtos.add(new UserFollowResponseDto(
                follow1.getFollower().getUserId(),
                follow1.getFollower().getNickname(),
                follow1.getFollower().getProfile()));

        // stubbing
        when(followRepository.findByUserId_UserId(userId)).thenReturn(followers);

        // when

        List<UserFollowResponseDto> checkDtos = mypageService.getUserFollowers(userId);

        // then
        assertEquals(userFollowResponseDtos,checkDtos);
        verify(followRepository).findByUserId_UserId(userId);
    }

    @Test
    @DisplayName("팔로잉 여부 확인")
    public void getFollowingsTest(){
        // given
        String userId = "user1"; // 팔로잉 목록을 조회할 유저 ID

        User user1 = mock(User.class);
        User followed1 = mock(User.class);

        Follow follow1 = new Follow(new FollowId(
                followed1.getUserId(),user1.getUserId()),
                followed1,user1);

        List<Follow> followers = new ArrayList<>();
        followers.add(follow1);

        List<UserFollowResponseDto> userFollowResponseDtos = new ArrayList<>();
        userFollowResponseDtos.add(new UserFollowResponseDto(
                follow1.getUserId().getUserId(),
                follow1.getUserId().getNickname(),
                follow1.getUserId().getProfile()));

        //stubbing
        when(followRepository.findByFollower_UserId(userId)).thenReturn(followers);

        // when
        List<UserFollowResponseDto> checkDtos = mypageService.getUserFollowings(userId);

        // then
        assertEquals(userFollowResponseDtos,checkDtos);
        verify(followRepository).findByFollower_UserId(userId);
    }

    @Test
    @DisplayName("회원 일반 게시물 조회")
    public void getGeneralPostsTest(){
        // given
        String userId = "user1"; // 조회할 게시물을 작성한 유저의 ID

        GeneralPost generalPost1 = mock(GeneralPost.class);
        User user = mock(User.class);
        List<GeneralPost> posts = new ArrayList<>();

        List<GeneralPostListResponseDto> generalPostListResponseDtos = new ArrayList<>();

        // stubbing
        when(generalPost1.getUser()).thenReturn(user);
        when(generalPostRepository.findByUser_UserId(userId)).thenReturn(posts);

        // when
        posts.add(generalPost1);
        generalPostListResponseDtos.add(GeneralPostListResponseDto.createDto(generalPost1));
        List<GeneralPostListResponseDto> checkDtos = mypageService.getUserPosts(userId);

        // then
        assertEquals(generalPostListResponseDtos,checkDtos);
    }


    @Test
    @DisplayName("회원 공모전 게시물 조회")
    public void getContestPostsTest(){
        // given
        String userId = "user1"; // 조회할 게시물을 작성한 유저의 ID

        ContestPost contestPost = mock(ContestPost.class);
        User user = mock(User.class);
        Contest contest = mock(Contest.class);
        List<ContestPost> posts = new ArrayList<>();

        List<UserContestPostsResponseDto> generalPostListResponseDtos = new ArrayList<>();

        //stubbing
        when(contestPost.getUser()).thenReturn(user);       // contestPost.getUser() 시 user 객체 리턴
        when(contestPost.getContest()).thenReturn(contest); // contestPost.getContest() 시 contest 객체 리턴
        when(contestPostRepository.findByUser_UserId(userId)).thenReturn(posts);

        // when
        posts.add(contestPost);                       // userId로 레포지토리 조회 시 posts 리스트 리턴
        generalPostListResponseDtos.add(UserContestPostsResponseDto.createDto(contestPost));
        List<UserContestPostsResponseDto> checkDtos = mypageService.getUserContests(userId);

        // then

        assertEquals(generalPostListResponseDtos,checkDtos);
    }


    @Test
    @DisplayName("회원 스크랩 목록 조회")
    public void getUserScrapsTest(){
        // given
        String userId = "user1"; // 조회할 게시물을 작성한 유저의 ID

        Scrap scrap = mock(Scrap.class);
        User user = mock(User.class);
        GeneralPost generalPost = mock(GeneralPost.class);

        List<Scrap> scraps = new ArrayList<>();
        List<UserPostsResponseDto> userPostsResponseDtos = new ArrayList<>();

        // stubbing
        when(scrap.getGeneralPost()).thenReturn(generalPost);   // scrap.getGeneralPost() 시 generalPost 객체 리턴
        when(scrap.getUser()).thenReturn(user);                 // scrap.getUser() 시 user 객체 리턴
        when(generalPost.getUser()).thenReturn(user);           // generalPost.getUser() 시 user 객체 리턴
        when(scrapRepository.findByUser_UserId(userId)).thenReturn(scraps);

        // when
        scraps.add(scrap);                       // userId로 레포지토리 조회 시 scraps 리스트 리턴
        userPostsResponseDtos.add(UserPostsResponseDto.createScrapsDto(scrap));
        List<UserPostsResponseDto> checkDtos = mypageService.getUserScraps(userId);

        // then
        assertEquals(userPostsResponseDtos,checkDtos);
    }

    @Test
    @DisplayName("회원 좋아요 목록 조회")
    public void getUserGeneralLikesTest(){
        // given
        String userId = "user1";

        GeneralLike generalLike = mock(GeneralLike.class);
        GeneralPost generalPost = mock(GeneralPost.class);
        User user = mock(User.class);
        List<GeneralLike> likes = new ArrayList<>();

        List<UserPostsResponseDto> userPostsResponseDtos = new ArrayList<>();

        // stubbing
        when(generalLike.getGeneralPost()).thenReturn(generalPost);
        when(generalLike.getUser()).thenReturn(user);
        when(generalPost.getUser()).thenReturn(user);
        when(generalLikeRepository.findByUser_UserId(userId)).thenReturn(likes);


        // when
        likes.add(generalLike);
        userPostsResponseDtos.add(UserPostsResponseDto.createGeneralLikesDto(generalLike));
        List<UserPostsResponseDto> checkDtos = mypageService.getUserGeneralLikes(userId);

        // then
        assertEquals(userPostsResponseDtos,checkDtos);
    }


    @Test
    @DisplayName("회원 공모전 좋아요 목록 조회")
    public void getUserContestLikesTest(){
        // given
        String userId = "user1";

        ContestLike contestLike = mock(ContestLike.class);
        ContestPost contestPost = mock(ContestPost.class);
        User user = mock(User.class);
        List<ContestLike> likes = new ArrayList<>();

        List<UserPostsResponseDto> userPostsResponseDtos = new ArrayList<>();

        // stubbing
        when(contestLike.getContestPost()).thenReturn(contestPost);
        when(contestLike.getUser()).thenReturn(user);
        when(contestPost.getUser()).thenReturn(user);
        when(contestLikeRepository.findByUser_UserId(userId)).thenReturn(likes);

        // when
        likes.add(contestLike);
        userPostsResponseDtos.add(UserPostsResponseDto.createContestLikesDto(contestLike));
        List<UserPostsResponseDto> checkDtos = mypageService.getUserContestLikes(userId);

        // then
        assertEquals(userPostsResponseDtos,checkDtos);
    }
}
