package com.example.kp_assignment.repository;

import com.example.kp_assignment.entity.Receive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiveRepository extends JpaRepository<Receive, Long> {
    List<Receive> findByToken(String token);

    List<Receive> findByTokenAndSpreadId(String token, Long userId);
}
