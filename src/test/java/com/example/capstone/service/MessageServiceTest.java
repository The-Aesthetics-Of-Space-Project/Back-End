package com.example.capstone.service;

import com.example.capstone.dto.ChatRoomDto;
import com.example.capstone.dto.MessageDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.capstone.entity.chat.Message;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    ChatRoomRepository chatRoomRepository;

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    ChatroomService chatroomService;

    @InjectMocks
    MessageService messageService;

    /*
     * messageService.sendMessage 테스트 코드
     */
    @Test
    void testSendMessage() {
        // Given
        String roomId = "qwer";
        String sender = "jerry7094";
        String receiver = "LOLGUN";
        String content = "안녕하세요";

        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setPerson1(sender);
        chatRoomDto.setPerson2(receiver);
        Chatroom chatroom = chatRoomDto.toEntity();

        MessageDto messageDto = new MessageDto();
        messageDto.setRoomid(roomId);
        messageDto.setSender(sender);
        messageDto.setReciver(receiver);
        messageDto.setContent(content);

        Message message = new Message(content, sender, receiver);
        message.setChatRoom(chatroom);

        // Mocking
        when(chatRoomRepository.findById(eq(roomId))).thenReturn(Optional.of(chatroom));
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        // When
        MessageDto result = messageService.sendMessage(messageDto);

        // Then
        assertEquals(messageDto, result);
        verify(chatRoomRepository).findById(eq(roomId));
        verify(messageRepository).save(any(Message.class));
    }
}
