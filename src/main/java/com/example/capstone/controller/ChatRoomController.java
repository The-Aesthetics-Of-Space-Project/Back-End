package com.example.capstone.controller;

import com.example.capstone.dto.ChatRoomDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8083")
public class ChatRoomController {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserRepository userRepository;

    // 채팅방 생성 로직
    @GetMapping("/chatroom/{id}")
    public ResponseEntity<?> Chatroom(@PathVariable(value = "id") String id){
        log.info("id : " + id);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("list", getChatPartners(id));
        return ResponseEntity.ok().body(responseData);
    }

    private String getRoomId(String userNickname, String partnerNickname) {
        log.info("userNickname : {}, partnerNickname : {} " ,userNickname,partnerNickname );
        String roomId = chatRoomRepository.findroomid(userNickname, partnerNickname);
        if (roomId == null) {
            roomId = chatRoomRepository.findroomid(partnerNickname, userNickname);
        }

        if (roomId == null) {
            // 대화한 적이 없는 경우 새로운 roomid 생성
            roomId = createNewRoom(userNickname, partnerNickname);
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
        String nickname =userRepository.findNickname(id);
        List<String> test = chatRoomRepository.findPerson(nickname);
        List<String> test2 = chatRoomRepository.findPerson2(nickname);
        List<String> combined = Stream.concat(test.stream(), test2.stream())
                .collect(Collectors.toList());
        log.info(combined.toString());
        return combined;
    }

    // 메시지 읽어드리는 로직
    @ResponseBody
    @PostMapping("/api/chat_history/{partner}/{userid}")
    public Set<Message> getChatHistory(@PathVariable(value = "partner") String partner,
                                       @PathVariable(value = "userid") String userid) {

        String nickname =userRepository.findNickname(userid);
        String roomId = getRoomId(nickname, partner);

        log.info("partner: {}, userid: {}, roomid: {}", partner, nickname, roomId);

        if (roomId == null) {
            log.info("No chat room found for the given users.");
            return Collections.emptySet();
        }

        Optional<Chatroom> optionalChatRoom = chatRoomRepository.findById(roomId);
        if (optionalChatRoom.isPresent()) {
            Chatroom chatRoom = optionalChatRoom.get();
            Set<Message> messages = chatRoom.getMessages();
            if (messages == null) {
                messages = new HashSet<>();
            }
            messages.forEach(message -> log.info(message.toString()));
            return messages;
        } else {
            log.info("Chat room not found.");
            return Collections.emptySet();
        }
    }

    @ResponseBody
    @GetMapping("/chat/{id}")
    public String test(@PathVariable(value = "id") String id){
        log.info("여기 지나감, id : {}",id);
        String nickname =userRepository.findNickname(id);
        String nickname2 =userRepository.findNickname("jerry6475@naver.com");

        return getRoomId(nickname,nickname2);
    }

    @ResponseBody
    @PostMapping("/api/chat_room/{partner}/{userid}")
    public String room(@PathVariable(value = "partner") String partner,
                       @PathVariable(value = "userid") String userid) {
        log.info("chat_room. partner : {}, userid : {}", partner,userid);
        String nickname =userRepository.findNickname(userid);

        String roomId = getRoomId(nickname, partner);

        log.info("roomid : " + roomId);
        return roomId;
    }
}
