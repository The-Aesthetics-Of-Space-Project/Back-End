package com.example.capstone.repository;

import com.example.capstone.entity.interior.InteriorStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InteriorStyleRepository extends JpaRepository<InteriorStyle, Integer> {
    Optional<InteriorStyle> findByStyle(String style);
}
