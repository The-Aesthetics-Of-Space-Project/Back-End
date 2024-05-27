package com.example.capstone.controller;

import com.example.capstone.repository.ChatRoomRepository;
import com.example.capstone.repository.InteriorStyleRepository;
import com.example.capstone.repository.MessageRepository;
import com.example.capstone.repository.UserRepository;
import com.example.capstone.service.ChatroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatRoomController.class)
@ExtendWith(SpringExtension.class)
public class ChatRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatRoomRepository chatRoomRepository;

    @MockBean
    private ChatroomService chatroomService;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private InteriorStyleRepository interiorStyleRepository;

    private static final String BASE_URL = "/api";

    @Test
    @DisplayName("ChatRoomController.Chatroom 메서드 test코드")
    public void testGetChatPartners() throws Exception {
        // given
        String userId = "user1";
        Map<String, Object> lst = new HashMap<>();
        List<String> test1 = new ArrayList<>();
        List<String> test2 = new ArrayList<>();
        test1.add("user2");
        test1.add("user3");
        test2.add("user4");
        test2.add("user5");
        List<String> combined = Stream.concat(test1.stream(), test2.stream()).collect(Collectors.toList());
        lst.put("combin", combined);

        when(chatroomService.getChatPartners(userId)).thenReturn(lst);

        // when, then
        mockMvc.perform(get("/chatroom/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.combin[0]").value("user2"))
                .andExpect(jsonPath("$.combin[1]").value("user3"))
                .andExpect(jsonPath("$.combin[2]").value("user4"))
                .andExpect(jsonPath("$.combin[3]").value("user5"));
    }

    @Test
    @DisplayName("ChatRoomController.getChatHistory 메서드 test코드")
    public void testGetChatHistory() throws Exception {

        // given
        String partner = "partner1";
        String userId = "user1";

        // when
        when(chatroomService.getChatList(partner, userId)).thenReturn(new HashSet<>());

        // then
        mockMvc.perform(get(BASE_URL + "/chat_history/" + partner + "/" + userId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("ChatRoomController.test 메서드 test코드")
    public void testCreateChat() throws Exception {

        // given
        String id = "user1";

        // when
        when(chatroomService.createChat(id)).thenReturn("Chat Created");

        // then
        mockMvc.perform(get("/chat/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Chat Created"));
    }

    @Test
    @DisplayName("ChatRoomController.room 메서드 test코드")
    public void testGetRoom() throws Exception {

        // given
        String partner = "partner1";
        String userId = "user1";

        // when
        when(chatroomService.getRoom(partner, userId)).thenReturn("Room ID");

        // then
        mockMvc.perform(post(BASE_URL + "/chat_room/" + partner + "/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().string("Room ID"));
    }
}
