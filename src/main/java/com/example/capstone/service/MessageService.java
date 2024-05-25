package com.example.capstone.service;

import com.example.capstone.dto.MessageDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageRepository messageRepository;

    /*
     * 메세지 저장
     **/
    public MessageDto sendMessage(MessageDto dto){
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
