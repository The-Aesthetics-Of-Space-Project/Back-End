package com.example.capstone.service;

import com.example.capstone.dto.response.InteriorStyleResponseDto;
import com.example.capstone.repository.InteriorStyleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteriorStyleService {
    private final InteriorStyleRepository interiorStyleRepository;

    /**
     * 인테리어 스타일 테스트 결과 조회
     */
    @Transactional
    public InteriorStyleResponseDto getStyle(Integer id) {
        return interiorStyleRepository.findById(id)
                .map(InteriorStyleResponseDto::toDto)
                .orElseThrow(() -> new EntityNotFoundException("인테리어 스타일 결과가 존재하지 않습니다."));
    }
}
