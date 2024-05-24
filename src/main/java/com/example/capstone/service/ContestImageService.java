package com.example.capstone.service;

import com.example.capstone.entity.community.general.article.GeneralImage;
import com.example.capstone.repository.ContestImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContestImageService {
    private ContestImageRepository contestImageRepository;

    private final Path root = Paths.get("C:\\Temp\\ContestPostImage");

    /**
     * 이미지 파일 업로드
     * 이미지 -> 접근 가능한 url
     */
//    @Transactional
//    public String uploadImage(MultipartFile file) throws IOException {
//        if(!Files.exists(root)) {
//            Files.createDirectories(root);
//        }
//
//        /**
//         * 현재 시간의 타임스탬프 + 고유한 식별자 조합하여 생성 -> 파일 이름 중복 방지
//         * 파일이 저장될 전체 경로 생성
//         * 업로드된 파일을 지정된 경로에 저장
//         */
//        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID();
//        String fileExt = file.getContentType().split("/")[1]; // 확장명 추출 image/png -> ["image", "png"]
//        Path filePath = this.root.resolve(fileName + "." + fileExt).normalize();
//        Files.copy(file.getInputStream(), filePath);
//
//        GeneralImage generalImage = GeneralImage.builder()
//                .imagePath(filePath.toString())
//                .build();
//
//        GeneralImage savedImage = generalImageRepository.save(generalImage);
//
//        return "/api/general/post/image/" + savedImage.getImageId();
//    }
//
//    /**
//     * 이미지 조회
//     */
//    @Transactional public Resource getImage(Integer imageId) throws Exception {
//        GeneralImage generalImage = generalImageRepository.findById(imageId)
//                .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다."));
//
//        Path imagePath = Paths.get(generalImage.getImagePath());
//        return new UrlResource(imagePath.toUri());
//    }

}
