package com.example.capstone.repository;

import com.example.capstone.entity.chat.Chatroom;
import com.example.capstone.entity.chat.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 세팅
        Chatroom chatroom1 = new Chatroom("room1", "person1", "person2");
        entityManager.persist(chatroom1);

        // 시간 차이를 두고 메시지 생성
        Message message1 = new Message("Hello", "person1", "person2");
        message1.setChatRoom(chatroom1);
        message1.setTime(OffsetDateTime.now(ZoneOffset.UTC).minusMinutes(1));  // 현재 시간보다 1분 전

        Message message2 = new Message("Hi", "person2", "person1");
        message2.setChatRoom(chatroom1);
        message2.setTime(OffsetDateTime.now(ZoneOffset.UTC));  // 현재 시간

        entityManager.persist(message1);
        entityManager.persist(message2);
        entityManager.flush();
    }

    @Test
    @DisplayName("roomid로 메시지를 시간 순으로 찾기")
    public void whenFindByChatRoom_RoomidOrderByTime_thenReturnMessagesOrderedByTime() {
        // given
        String roomId = "room1";

        // when
        Set<Message> messageSet  = messageRepository.findByChatRoom_RoomidOrderByTime(roomId);

        // Set을 List로 변환하여 시간순으로 정렬
        List<Message> messages = new ArrayList<>(messageSet);
        messages.sort(Comparator.comparing(Message::getTime));

        // then
        assertThat(messages).isNotEmpty();
        assertThat(messages.get(0).getTime()).isBefore(messages.get(1).getTime());
    }
}
