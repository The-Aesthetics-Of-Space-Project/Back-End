package com.example.capstone.repository;

import com.example.capstone.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * nickname으로 user 찾는 메서드
     */
     Optional<User> findByNickname(String nickname);



    /**
     * userId로 user 찾는 메서드
     */
    Optional<User> findByUserId(String userId);
}
