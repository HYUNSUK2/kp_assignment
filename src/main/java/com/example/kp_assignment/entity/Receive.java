package com.example.kp_assignment.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "RECEIVE")
public class Receive {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Long receiveId;

    @Column
    private Long spreadId;

    @Column
    private String roomId;

    @Column
    private Long receivedAmount;

    @Column
    private String token;

    @Column
    private LocalDateTime receivedTime;

    @Column
    private LocalDateTime createdTime;
}
