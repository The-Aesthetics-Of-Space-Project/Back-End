package com.example.capstone.dto.request.contest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestPostUpdateRequestDto {

    private String contest;
    private String title;
    private String thumbnail;
    private String contents;
}
