package com.example.capstone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserFollowerResponseDto {
    private List<String> followers;

}
