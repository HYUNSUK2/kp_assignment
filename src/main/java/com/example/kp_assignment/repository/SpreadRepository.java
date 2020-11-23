package com.example.kp_assignment.repository;

import com.example.kp_assignment.entity.Spread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface SpreadRepository extends JpaRepository<Spread, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Spread findByToken(String token);
}
