package com.example.capstone.controller;

import com.example.capstone.dto.response.InteriorStyleResponseDto;
import com.example.capstone.service.InteriorStyleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class InteriorStyleController {
    private final InteriorStyleService interiorStyleService;

    /**
     * 인테리어 스타일 테스트 결과 조회
     */
    @Tag(name = "Interior Style Controller : 인테리어 스타일 테스트", description = "InteriorStyle Controller")
    @Operation(summary = "인테리어 스타일 테스트 결과 조회", description = "사용자가 인테리어 스타일 테스트 결과를 조회할 때 사용하는 API")
    @GetMapping("/api/interior/style/{id}")
    public InteriorStyleResponseDto getStyle(@PathVariable Integer id) {
        return interiorStyleService.getStyle(id);
    }

    /**
     * 파일명으로 이미지 파일 조회
     */
    @Tag(name = "Interior Style Controller : 인테리어 스타일 테스트", description = "InteriorStyle Controller")
    @Operation(summary = "인테리어 스타일 테스트 결과에서 이미지 조회", description = "사용자가 인테리어 스타일 테스트 결과 페이지에서 이미지를 조회할 때 사용하는 API")
    @GetMapping("/api/interior/style/image/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws Exception {
        Resource resource = interiorStyleService.getImage(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(fileName)))
                .body(resource);
    }
}
