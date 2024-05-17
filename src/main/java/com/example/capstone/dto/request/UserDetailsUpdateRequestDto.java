package com.example.capstone.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsUpdateRequestDto {

    private String userId;

    private String nickname;

    private MultipartFile profile;


}