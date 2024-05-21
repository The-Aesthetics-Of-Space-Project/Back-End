package com.example.capstone.controller;

import com.example.capstone.dto.ChatRoomDto;
import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import com.example.capstone.repository.ChatRoomRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    기능 : 채팅방 컨트롤러
    주요 기능 : 채팅방 CRUD 기능을 처리하는 페이지로 연결
    참조 : 이후 서비스 폴더로 분할시 로직 분할 다시 해야함
 */
@Slf4j
@Controller
public class ChatRoomController {
    @Autowired
    ChatRoomRepository chatRoomRepository;
    // 채팅방 생성 로직
    @GetMapping("/chatroom/{id}")
    public List<String> Chatroom(HttpSession session, Model model,@PathVariable String id){
        String sessionId = (String) session.getAttribute("sessionId");

        // 대화 중인 상대인지 체크하고 roomid 가져오기
        String roomId = getRoomId(sessionId, id, model);

        // 채팅 상대 내역 가져와서 전달
        List<String> combined = getChatPartners(sessionId, id);
        combined.add(id);

        // 채팅 상대가 없는 경우 처리
        if (!combined.isEmpty()) {
            model.addAttribute("list", combined);
        }

        model.addAttribute("sessionId", sessionId);
        return combined;
    }

    private String getRoomId(String sessionId, String id, Model model) {
        String roomId = chatRoomRepository.findroomid(sessionId, id);
        if (roomId == null) {
            roomId = chatRoomRepository.findroomid(id, sessionId);
        }

        if (roomId == null) {
            // 대화한 적이 없는 경우 새로운 roomid 생성
            roomId = createNewRoom(sessionId, id, model);
        } else {
            // 이미 대화한 적 있는 경우 해당 roomid를 모델에 추가
            model.addAttribute("chatroom", roomId);
            log.info("roomid:" + roomId);
        }

        return roomId;
    }

    private String createNewRoom(String sessionId, String id, Model model) {
        ChatRoomDto chatroomdto = new ChatRoomDto();
        chatroomdto.setPerson1(sessionId);
        chatroomdto.setPerson2(id);
        String roomId = chatroomdto.getRoomid();
        model.addAttribute("chatroom", roomId);
        log.info("roomid:" + roomId);

        // entity 변환 후 저장
        Chatroom chatroom = chatroomdto.toEntity();
        chatRoomRepository.save(chatroom);

        return roomId;
    }

    private List<String> getChatPartners(String sessionId, String id) {
        List<String> test = chatRoomRepository.findPerson(sessionId);
        List<String> test2 = chatRoomRepository.findPerson2(sessionId);

        List<String> combined = Stream.concat(test.stream(), test2.stream())
                .collect(Collectors.toList());
        return combined;
    }


    // 메시지 읽어드리는 로직
    @ResponseBody
    @RequestMapping("/api/chat_history")
    public Set<Message> somthing(@RequestParam String partner, HttpSession session){

        String sessionid = (String) session.getAttribute("sessionId");
        String roomid=null;
        log.info("partner name : " + partner);
        Optional<Chatroom> optionalChatRoom = null;
        Set<Message> messages = null;
        log.info(sessionid);
        log.info(chatRoomRepository.findroomid(partner,sessionid) + "," + chatRoomRepository.findroomid(sessionid,partner));

        if(chatRoomRepository.findroomid(partner,sessionid) != null || chatRoomRepository.findroomid(sessionid,partner)!=null){
            if(chatRoomRepository.findroomid(partner,sessionid) != null){
                roomid = chatRoomRepository.findroomid(partner,sessionid);
            }else{
                roomid = chatRoomRepository.findroomid(sessionid,partner);
            }

            log.info("roomid : " + roomid);
            optionalChatRoom = chatRoomRepository.findById(roomid);
        }

        log.info("optionalChatRoom : " + optionalChatRoom );

        if (optionalChatRoom != null && optionalChatRoom.isPresent()) {  // 값이 있는 경우
            Chatroom chatRoom = optionalChatRoom.get();
            messages = chatRoom.getMessages();
            // messages의 내용을 로그에 출력합니다.
            messages.forEach(message -> log.info(message.toString()));
        } else {
            // 값이 없는 경우 처리
            log.info("null임");
        }


        return messages;
    }

    @ResponseBody  // @ResponseBody 추가
    @RequestMapping("/api/chat_room")
    public String room(@RequestParam String partner,HttpSession session){
        String sessionid = (String) session.getAttribute("sessionId");
        String newroomid=null;
        if(chatRoomRepository.findroomid(partner,sessionid) != null || chatRoomRepository.findroomid(sessionid,partner)!=null){
            if(chatRoomRepository.findroomid(partner,sessionid) != null){
                newroomid = chatRoomRepository.findroomid(partner,sessionid);
            }else{
                newroomid = chatRoomRepository.findroomid(sessionid,partner);
            }

            log.info("roomid : " + newroomid);
        }
        return newroomid;
    }
}
