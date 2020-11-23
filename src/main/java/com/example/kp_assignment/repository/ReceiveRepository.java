package com.example.kp_assignment.repository;

import com.example.kp_assignment.entity.Receive;
import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface ReceiveRepository extends JpaRepository<Receive, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Receive> findByToken(String token);

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Receive> findByTokenAndSpreadIdAndReceiveIdIsNotNull(String token, Long userId);
}
