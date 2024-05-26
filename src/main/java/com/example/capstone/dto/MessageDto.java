package com.example.capstone.dto;

import com.example.capstone.entity.chat.Message;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
/*
    기능 : 사용자에게 입력받는 메시지 정보
    주요 기능 : entity 변환 기능, 생정자를 통해 입력받은 정보를 담아두는 기능
 */
public class MessageDto {
    public String content;
    public String sender;
    public String reciver;
    public String roomid;
    public Message toEntity(){
        return new Message(content,sender,reciver);
    }
}
