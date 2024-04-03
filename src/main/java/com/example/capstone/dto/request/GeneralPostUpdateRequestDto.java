package com.example.capstone.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Blob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPostUpdateRequestDto {
    private String title;
    private Blob thumbnail;
    private String content;
}
