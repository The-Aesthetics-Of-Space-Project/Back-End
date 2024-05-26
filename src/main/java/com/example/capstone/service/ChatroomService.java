package com.example.capstone.service;

import com.example.capstone.dto.ChatRoomDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.MessageRepository;
import com.example.capstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatroomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

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

    /*
     *  채팅방 생성
     * */
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

    /*
     *  대화 상대 모두 출력
     * */
    public Map<String, Object> getChatPartners(String id) {
        String nickname = userRepository.findNickname(id);
        List<String> test = chatRoomRepository.findPerson(nickname);
        List<String> test2 = chatRoomRepository.findPerson2(nickname);
        List<String> combined = Stream.concat(test.stream(), test2.stream())
                .collect(Collectors.toList());
        log.info(combined.toString());
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("list", combined);
        return responseData;
    }

    /*
     *  채팅 내용 모두 출력
     * */
    public Set<Message> getChatList(String partner, String userid){
        String nickname = userRepository.findNickname(userid);
        String roomId = getRoomId(nickname, partner);

        log.info("partner: {}, userid: {}, roomid: {}", partner, nickname, roomId);

        if (roomId == null) {
            log.info("No chat room found for the given users.");
            return Collections.emptySet();
        }

        Optional<Chatroom> optionalChatRoom = chatRoomRepository.findById(roomId);
        if (optionalChatRoom.isPresent()) {
            Chatroom chatRoom = optionalChatRoom.get();
            Set<Message> messages = messageRepository.findByChatRoom_RoomidOrderByTime(chatRoom.getRoomid());
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

    /*
     *  RoomId 읽기
     * */
    public String getRoom(String partner,String userid){
        log.info("chat_room. partner : {}, userid : {}", partner,userid);
        String nickname =userRepository.findNickname(userid);

        String roomId = getRoomId(nickname, partner);

        log.info("roomid : " + roomId);
        return roomId;
    }

    /*
    *  채팅 시작하기
    * */
    public String createChat(String id){
        String nickname =userRepository.findNickname(id);
        String nickname2 =userRepository.findNickname("whswls@gmail.com");

        return getRoomId(nickname,nickname2);
    }

}
