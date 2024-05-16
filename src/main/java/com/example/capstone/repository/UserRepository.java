package com.example.capstone.repository;

import com.example.capstone.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    /**
     * nickname으로 user 찾는 메서드
     */
     Optional<User> findByNickname(String nickname);

    User findByUserId(String email);

}
