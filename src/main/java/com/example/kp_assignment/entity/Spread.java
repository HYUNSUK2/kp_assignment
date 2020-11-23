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
@Table(name = "SPREAD")
public class Spread {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long amount;

    @Column
    private Integer peopleNumber;

    @Column
    private String roomId;

    @Column
    private String token;

    @Column
    private LocalDateTime createdTime;
}
