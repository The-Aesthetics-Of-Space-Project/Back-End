package com.example.capstone.service;

import com.example.capstone.repository.GeneralLikeRepository;
import com.example.capstone.repository.GeneralPostRepository;
import com.example.capstone.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ScrapServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GeneralLikeService generalLikeService;

    @Mock
    private GeneralPostRepository generalPostRepository;

    @Mock
    private GeneralLikeRepository generalLikeRepository;
}
