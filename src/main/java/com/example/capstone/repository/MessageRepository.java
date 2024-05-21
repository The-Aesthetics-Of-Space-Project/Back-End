package com.example.capstone.repository;


import com.example.capstone.entity.chat.Message;
import org.springframework.data.repository.CrudRepository;
/*
    기능 : 메시지 리파지로티
    주요 기능 : 메시지 CRUD 기능 실질적 수행
 */
public interface MessageRepository extends CrudRepository<Message,String> {

}
