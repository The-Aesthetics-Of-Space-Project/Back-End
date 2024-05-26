package com.example.capstone.controller;

import com.example.capstone.entity.chat.Message;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.MessageRepository;
import com.example.capstone.repository.UserRepository;
import com.example.capstone.service.ChatroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ChatRoomController {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatroomService chatroomService;

    @Autowired
    UserRepository userRepository;

    // 채팅방 생성 로직
    @Tag(name = "Chat Controller : 채팅방", description = "Chat Post Controller")
    @Operation(summary = "대화 상대 목록 전체 조회", description = "사용자가 대화 상대 전체 조회할 때 사용하는 API")
    @GetMapping("/chatroom/{id}")
    public ResponseEntity<?> Chatroom(@PathVariable(value = "id") String id){
        return ResponseEntity.ok().body(chatroomService.getChatPartners(id));
    }

    // 메시지 읽어드리는 로직
    @Tag(name = "Chatromm Controller : 채팅방", description = "Chatroom Controller")
    @Operation(summary = "메세지 목록 전체 조회", description = "사용자가 상대와의 메세지 목록 전체 조회할 때 사용하는 API")
    @ResponseBody
    @GetMapping("/api/chat_history/{partner}/{userid}")
    public Set<Message> getChatHistory(@PathVariable(value = "partner") String partner,
                                       @PathVariable(value = "userid") String userid) {
        return chatroomService.getChatList(partner,userid);
    }

    @Tag(name = "Chatroom Controller : 채팅방", description = "Chatroom Controller")
    @Operation(summary = "채팅방 생성로직(미완)", description = "사용자가 상대와 채팅을 시작할 때 사용하는 API")
    @ResponseBody
    @GetMapping("/chat/{id}")
    public String test(@PathVariable(value = "id") String id){
        return chatroomService.createChat(id);
    }

    @Tag(name = "Chatroom Controller : 채팅방", description = "Chatroom Controller")
    @Operation(summary = "채팅방 ID값 조회 및 생성" , description = "사용자가 대화방의 ID를 조회 및 생성할 때 사용하는 API")
    @ResponseBody
    @PostMapping("/api/chat_room/{partner}/{userid}")
    public String room(@PathVariable(value = "partner") String partner,
                       @PathVariable(value = "userid") String userid) {
        return chatroomService.getRoom(partner,userid);
    }
}
