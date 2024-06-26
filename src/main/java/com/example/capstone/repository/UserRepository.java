package com.example.capstone.repository;

import com.example.capstone.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    boolean existsByNickname(String nickname);

    @Query("SELECT password FROM User WHERE userId = :userId")
    String findPW(@Param("userId") String userId);

    @Query("SELECT userId FROM User WHERE userId = :userId" )
    String findID(@Param("userId") String userId);

    @Query("SELECT nickname FROM User WHERE userId = :userId" )
    String findNickname(@Param("userId") String userId);


}
