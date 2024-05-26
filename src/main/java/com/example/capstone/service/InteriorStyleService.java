package com.example.capstone.service;

import com.example.capstone.dto.response.InteriorStyleResponseDto;
import com.example.capstone.repository.GeneralImageRepository;
import com.example.capstone.repository.InteriorStyleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class InteriorStyleService {
    private final InteriorStyleRepository interiorStyleRepository;
    private final GeneralImageRepository generalImageRepository;

    // 파일이 저장되어있는 디렉토리 경로
    private final Path root = Paths.get("C:\\Temp\\InteriorStyleImage");

    /**
     * 인테리어 스타일 테스트 결과 조회
     */
    @Transactional
    public InteriorStyleResponseDto getStyle(Integer id) {
        return interiorStyleRepository.findById(id)
                .map(InteriorStyleResponseDto::toDto)
                .orElseThrow(() -> new EntityNotFoundException("인테리어 스타일 결과가 존재하지 않습니다."));
    }

    /**
     * 파일명으로 이미지 파일 조회
     */
    @Transactional
    public Resource getImage(String fileName) throws Exception{
        Path filePath = root.resolve(fileName).normalize();

        return new UrlResource(filePath.toUri());
    }
}
