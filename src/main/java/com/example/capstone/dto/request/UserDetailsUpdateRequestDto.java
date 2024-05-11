package com.example.capstone.dto.request;


import com.example.capstone.entity.user.User;
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
    private String nickname;


    private String password;


    private MultipartFile profile;

}
