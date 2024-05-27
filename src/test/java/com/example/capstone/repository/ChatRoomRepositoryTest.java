package com.example.capstone.repository;

import com.example.capstone.entity.chat.Chatroom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class ChatRoomRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 세팅
        Chatroom chatroom1 = new Chatroom("room1", "person1", "person2");
        Chatroom chatroom2 = new Chatroom("room2", "person2", "person3");
        entityManager.persist(chatroom1);
        entityManager.persist(chatroom2);
        entityManager.flush();
    }

    @Test
    @DisplayName("채팅방에서 대화 상대1 찾기")
    public void whenFindPerson_thenReturnPerson2() {
        // when
        List<String> foundPersons = chatRoomRepository.findPerson("person1");

        // then
        assertThat(foundPersons).hasSize(1).contains("person2");
    }

    @Test
    @DisplayName("채팅방에서 대화 상대2 찾기")
    public void whenFindPerson2_thenReturnPerson1() {
        // when
        List<String> foundPersons = chatRoomRepository.findPerson2("person2");

        // then
        assertThat(foundPersons).hasSize(1).contains("person1");
    }

    @Test
    @DisplayName("사람 두명을 이용한 상대2 찾기")
    public void whenFind_thenReturnPerson2() {
        // when
        String foundPerson = chatRoomRepository.find("person1", "person2");

        // then
        assertThat(foundPerson).isEqualTo("person2");
    }

    @Test
    @DisplayName("roomid 찾기")
    public void whenFindroomid_thenReturnRoomId() {
        // when
        String roomId = chatRoomRepository.findroomid("person1", "person2");

        // then
        assertThat(roomId).isEqualTo("room1");
    }
}
