package com.example.capstone.controller;

import com.example.capstone.dto.MessageDto;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.InteriorStyleRepository;
import com.example.capstone.repository.MessageRepository;
import com.example.capstone.service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Mock
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private WebSocketStompClient stompClient;

    @MockBean
    private InteriorStyleRepository interiorStyleRepository;

    private BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();

    @Test
    void testSendMessage() throws Exception {
        // Given
        String roomid = "roomId";
        String content = "Test message";

        MessageDto messageDto = new MessageDto();
        messageDto.setReciver("LOLGUN");
        messageDto.setSender("jerry7094");
        messageDto.setRoomid(roomid);
        messageDto.setContent(content);

        when(messageService.sendMessage(any(MessageDto.class))).thenReturn(messageDto);

        // When
        StompSession stompSession = stompClient.connectAsync("ws://localhost:8080/gs-guide-websocket", new StompSessionHandlerAdapter() {
        }).get(1, SECONDS);
        stompSession.subscribe("/pub/" + roomid, new DefaultStompFrameHandler());

        stompSession.send("/app/" + roomid, messageDto.toString().getBytes());

        // Then
        String message = blockingQueue.poll(1, SECONDS);
        assertThat(message).isNotNull();
        assertThat(message).contains(roomid, content);
    }

    private class DefaultStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            blockingQueue.offer(new String((byte[]) payload));
        }
    }
}
