package com.example.capstone.controller;

import com.example.capstone.dto.MessageDto;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.MessageRepository;
import com.example.capstone.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;


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

    @Autowired
    private MessageService messageService;

    @Tag(name = "MessageController : 이미지 분석", description = "Message Controller")
    @Operation(summary = "구독 관계 정의 및 메세지 등록", description = "사용자가 전송한 메세지를 저장 및 구독중인 페이지로 전달하는 API")
    @MessageMapping("/{roomId}")
    @SendTo("/pub/{roomId}")
    public MessageDto send(@DestinationVariable(value = "roomId") String roomId, @RequestBody MessageDto dto) {
        return messageService.sendMessage(dto);
    }
}
