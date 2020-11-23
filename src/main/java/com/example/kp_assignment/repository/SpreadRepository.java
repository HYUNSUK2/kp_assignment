package com.example.kp_assignment.repository;

import com.example.kp_assignment.entity.Spread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpreadRepository extends JpaRepository<Spread, Long> {
    String findByToken(String token);
}
