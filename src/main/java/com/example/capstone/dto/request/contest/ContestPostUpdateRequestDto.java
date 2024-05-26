package com.example.capstone.dto.request.contest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestPostUpdateRequestDto {

    private String title;
    private MultipartFile thumbnail;
    private String contents;
}
