package com.example.capstone.controller;

import com.example.capstone.service.GeneralImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GeneralImageController {
    private final GeneralImageService generalImageService;

    /**
     * 이미지 파일 업로드
     * 이미지 -> 접근 가능한 url
     */
    @PostMapping("/api/general/post/image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("articleId") Integer articleId
    ) {

        try {
            String filePath = generalImageService.uploadImage(file, articleId);
            return ResponseEntity.ok(filePath);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("이미지 업로드 실패: " + e.getMessage());
        }
    }

    /**
     * 이미지 조회
     */
    @GetMapping("/api/general/post/image/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable Integer imageId) throws Exception {
        Resource resource = generalImageService.getImage(imageId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}