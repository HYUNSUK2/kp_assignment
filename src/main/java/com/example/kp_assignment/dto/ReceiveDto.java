package com.example.kp_assignment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiveDto {

    private long receivedAmount;

    private long receivedId;
}
