package com.example.capstone.service;

import com.example.capstone.entity.community.general.article.GeneralImage;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.repository.GeneralImageRepository;
import com.example.capstone.repository.GeneralPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class GeneralImageService {
    private final GeneralImageRepository generalImageRepository;
    private final GeneralPostRepository generalPostRepository;

    //업로드 된 파일이 저장될 디렉토리 경로
    private final Path root = Paths.get("C:\\Temp\\GeneralPostImage");

    /**
     * 이미지 파일 업로드
     * 이미지 -> 접근 가능한 url
     */
    @Transactional
    public String uploadImage(MultipartFile file, Integer articleId) throws IOException {
        /**
         * 현재 시간의 타임스탬프 + 원래 파일 이름 조합하여 생성 -> 파일 이름 중복 방지
         * 파일이 저장될 전체 경로 생성
         * 업로드된 파일을 지정된 경로에 저장
         */
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = this.root.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // 이미지 경로를 DB에 저장
        GeneralPost generalPost = generalPostRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다: " + articleId));

        GeneralImage generalImage = GeneralImage.builder()
                .imagePath(filePath.toString())
                .generalPost(generalPost)
                .build();

        GeneralImage savedImage = generalImageRepository.save(generalImage);

        return "/api/general/post/image/" + savedImage.getImageId();
    }

    /**
     * 이미지 조회
     */
    @Transactional public Resource getImage(Integer imageId) throws Exception {
        GeneralImage generalImage = generalImageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다."));

        Path imagePath = Paths.get(generalImage.getImagePath());
        return new UrlResource(imagePath.toUri());
    }
}

