package com.example.capstone.controller;

import com.example.capstone.dto.MessageDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/*
    기능 : 메시지 컨트롤러
    주요 기능 : 메시지 전송시 로직 처리
 */
@Slf4j
@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @MessageMapping("/{roomId}")
    @SendTo("/pub/{roomId}")
    public MessageDto send(@DestinationVariable(value = "roomId") String roomId, @RequestBody MessageDto dto) {
        log.info("Received MessageDto: " + dto);

        try {
            log.info("Received raw message: " + dto);
        } catch (Exception e) {
            log.error("Failed to log raw message", e);
        }

        Chatroom chatRoom = chatRoomRepository.findById(dto.getRoomid())
                .orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));

        Message message = new Message(dto.getContent(), dto.getSender(), dto.getReciver());
        message.setChatRoom(chatRoom);
        messageRepository.save(message);

        return dto;
    }
}

