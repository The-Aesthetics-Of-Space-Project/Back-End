package com.example.capstone.service;

import com.example.capstone.dto.request.GeneralLikeRequestDto;
import com.example.capstone.entity.community.general.article.GeneralImage;
import com.example.capstone.entity.community.general.article.GeneralPost;
import com.example.capstone.entity.user.User;
import com.example.capstone.repository.GeneralImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GeneralImageServiceTest {

    @Mock
    private GeneralImageRepository generalImageRepository;

    @InjectMocks
    private GeneralImageService generalImageService;


    @Test
    @DisplayName("일반 게시판 이미지 업로드 테스트")
    public void uploadImageTest()throws IOException {

        // given
        MockMultipartFile multipartFile = new MockMultipartFile(
                "postImage", "postImage.jpg", "image/jpeg", "test image content".getBytes());
        GeneralImage generalImage = mock(GeneralImage.class);
        // stubbing
        when(generalImageRepository.save(any(GeneralImage.class))).thenReturn(generalImage);

        // when
        generalImageService.uploadImage(multipartFile);

        // then
        verify(generalImageRepository).save(any(GeneralImage.class));
    }


    @Test
    @DisplayName("일반 게시판 이미지 조회 테스트")
    public void getImageTest()throws Exception {

        // given
        Integer imageId = 1;

        GeneralImage generalImage = new GeneralImage();
        generalImage.setImageId(imageId);
        generalImage.setImagePath("C:\\Temp\\GeneralPostImage\\image.jpg");

        // stubbing
        when(generalImageRepository.findById(imageId)).thenReturn(Optional.of(generalImage));

        // when
        Resource checkResource = new UrlResource(Paths.get(generalImage.getImagePath()).toUri());
        Resource resource = generalImageService.getImage(imageId);

        // then
        assertEquals(checkResource,resource);
    }



}
