package com.example.capstone.controller;

import com.example.capstone.dto.ChatRoomDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import com.example.capstone.repository.ChatRoomRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
public class ChatRoomController {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    // 채팅방 생성 로직
    @GetMapping("/chatroom/{id}")
    public ResponseEntity<?> Chatroom(@PathVariable(value = "id") String id){
        log.info("id : " + id);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("list", getChatPartners(id));
        return ResponseEntity.ok().body(responseData);
    }

    private String getRoomId(String sessionId, String id) {
        String roomId = chatRoomRepository.findroomid(sessionId, id);
        if (roomId == null) {
            roomId = chatRoomRepository.findroomid(id, sessionId);
        }

        if (roomId == null) {
            // 대화한 적이 없는 경우 새로운 roomid 생성
            roomId = createNewRoom(sessionId, id);
        } else {
            log.info("roomid:" + roomId);
        }

        return roomId;
    }

    private String createNewRoom(String sessionId, String id) {
        ChatRoomDto chatroomdto = new ChatRoomDto();
        chatroomdto.setPerson1(sessionId);
        chatroomdto.setPerson2(id);
        String roomId = chatroomdto.getRoomid();
        log.info("roomid:" + roomId);

        // entity 변환 후 저장
        Chatroom chatroom = chatroomdto.toEntity();
        chatRoomRepository.save(chatroom);

        return roomId;
    }

    private List<String> getChatPartners(String id) {
        List<String> test = chatRoomRepository.findPerson(id);
        List<String> test2 = chatRoomRepository.findPerson2(id);
        List<String> combined = Stream.concat(test.stream(), test2.stream())
                .collect(Collectors.toList());
        log.info(combined.toString());
        return combined;
    }

    // 메시지 읽어드리는 로직
    @ResponseBody
    @RequestMapping("/api/chat_history")
    public Set<Message> somthing(@RequestParam String partner, HttpSession session){
        String sessionId = (String) session.getAttribute("sessionId");
        String roomId = getRoomId(sessionId, partner);

        log.info("partner name : " + partner);
        log.info("sessionId : " + sessionId);
        log.info("roomid : " + roomId);

        if (roomId == null) {
            log.info("null임");
            return Collections.emptySet();
        }

        Optional<Chatroom> optionalChatRoom = chatRoomRepository.findById(roomId);
        if (optionalChatRoom.isPresent()) {
            Chatroom chatRoom = optionalChatRoom.get();
            Set<Message> messages = chatRoom.getMessages();
            messages.forEach(message -> log.info(message.toString()));
            return messages;
        } else {
            log.info("null임");
            return Collections.emptySet();
        }
    }

    @ResponseBody
    @RequestMapping("/api/chat_room")
    public String room(@RequestParam String partner, HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
        String roomId = getRoomId(sessionId, partner);

        log.info("roomid : " + roomId);
        return roomId;
    }
}
