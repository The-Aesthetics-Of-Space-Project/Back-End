package com.example.capstone.controller;

import com.example.capstone.service.GeneralImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GeneralImageController {
    private final GeneralImageService generalImageService;

    /**
     * 이미지 파일 업로드
     * 이미지 -> 접근 가능한 url
     */
    @Tag(name = "GeneralImage Controller : 일반 게시판 이미지", description = "General Image Controller")
    @Operation(summary = "이미지 파일 업로드", description = "사용자가 이미지를 업로드할 때 사용하는 API")
    @PostMapping("/api/general/post/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = generalImageService.uploadImage(file);
            return ResponseEntity.ok(filePath);
        } catch (IOException e) {
            log.info(e.toString());
            return ResponseEntity.status(500).body("이미지 업로드 실패: " + e.getMessage());
        }
    }

    /**
     * 이미지 조회
     */
    @Tag(name = "GeneralImage Controller : 일반 게시판 이미지", description = "General Image Controller")
    @Operation(summary = "이미지 조회", description = "사용자가 이미지를 조회할 때 사용하는 API")
    @GetMapping("/api/general/post/image/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable Integer imageId) throws Exception {
        Resource resource = generalImageService.getImage(imageId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}