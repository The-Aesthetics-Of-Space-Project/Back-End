package com.example.capstone.service;

import com.example.capstone.dto.ChatRoomDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import com.example.capstone.service.ChatroomService;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.MessageRepository;
import com.example.capstone.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ChatRoomServiceTest {

    @InjectMocks
    private ChatroomService chatroomService;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.openMocks(this); // This is not needed with @ExtendWith(MockitoExtension.class)
    }

    /*
    chatroomService.getRoomId 테스트 코드
     */
    @Test
    @DisplayName("GetRoomId 테스트")
    void testGetRoomId() {

        // given
        String userNickname = "jerry7094";
        String partnerNickname = "LOLGUN";
        String expectedRoomId = "qwer";

        // when
        when(chatRoomRepository.findroomid(userNickname, partnerNickname)).thenReturn(expectedRoomId);

        String roomId = chatroomService.getRoomId(userNickname, partnerNickname);

        // then
        assertEquals(expectedRoomId, roomId);
        verify(chatRoomRepository, times(1)).findroomid(userNickname, partnerNickname);
        verify(chatRoomRepository, never()).findroomid(partnerNickname, userNickname);
    }

    /*
    chatroomService.createNewRoom 테스트 코드
     */
    @Test
    @DisplayName("CreateNewRoom 테스트")
    void testCreateNewRoom() {

        // given
        String userNickname = "user1";
        String partnerNickname = "user2";

        // when
        when(chatRoomRepository.findroomid(userNickname, partnerNickname)).thenReturn(null);
        when(chatRoomRepository.findroomid(partnerNickname, userNickname)).thenReturn(null);
        when(chatRoomRepository.save(any(Chatroom.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String roomId = chatroomService.getRoomId(userNickname, partnerNickname);

        // then
        assertNotNull(roomId); // 여기서 roomId가 null이 아닌지 확인
        verify(chatRoomRepository, times(1)).findroomid(userNickname, partnerNickname);
        verify(chatRoomRepository, times(1)).findroomid(partnerNickname, userNickname);
        verify(chatRoomRepository, times(1)).save(any(Chatroom.class));
    }

    /*
    chatroomService.getChatPatner 테스트 코드
     */
    @Test
    @DisplayName("GetChatPartner 테스트")
    void testGetChatPartner(){

        // given
        String id = "jerry7094@naver.com";
        String nickname = "jerry7094";
        List<String> samplelst1 = Arrays.asList("LOLGUN","whswlsdlek");

        // Mocking UserRepository to return the nickname for the given id
        when(userRepository.findNickname(any(String.class))).thenReturn(nickname);

        // Mocking ChatRoomRepository to return lists of chat partners
        when(chatRoomRepository.findPerson(nickname)).thenReturn(samplelst1);

        // when
        Map<String, Object> result = chatroomService.getChatPartners(id);

        // then
        List<String> expectedCombinedList = new ArrayList<>();
        expectedCombinedList.addAll(samplelst1);

        assertEquals(expectedCombinedList, result.get("list"));
    }


    @Test
    @DisplayName("getChatList 테스트")
    void testGetChatList() {
        // given
        String userid = "user1";
        String partner = "partner1";
        String roomid = "qwer";
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setPerson2("jerry7094");
        chatRoomDto.setPerson1(partner);
        chatRoomDto.setRoomid(roomid);

        Chatroom chatroom = chatRoomDto.toEntity();

        Set<Message> expectedMessages = new HashSet<>();
        expectedMessages.add(new Message("Hello", "user1", "partner1"));

        // Mock userRepository의 findNickname 메서드 호출 결과 설정
        when(userRepository.findNickname(userid)).thenReturn("jerry7094");

        // Mock chatRoomRepository의 findroomid 메서드 호출 결과 설정
        when(chatRoomRepository.findroomid(any(String.class), any(String.class))).thenReturn("qwer");

        // Mock messageRepository의 findByChatRoom_RoomidOrderByTime 메서드 호출 결과 설정
        when(messageRepository.findByChatRoom_RoomidOrderByTime(roomid)).thenReturn(expectedMessages);

        // Mock chatRoomRepository의 findById 메서드 호출 결과 설정
        when(chatRoomRepository.findById("qwer")).thenReturn(Optional.of(chatroom));

        // when
        Set<Message> result = chatroomService.getChatList(partner, userid);

        // then
        verify(userRepository, times(1)).findNickname(userid);
        verify(chatRoomRepository, times(1)).findroomid("jerry7094", "partner1");
        verify(messageRepository, times(1)).findByChatRoom_RoomidOrderByTime(roomid);
    }
}
