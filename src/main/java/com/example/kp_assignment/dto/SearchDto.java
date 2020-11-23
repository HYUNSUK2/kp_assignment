package com.example.kp_assignment.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SearchDto {

    private LocalDateTime createdTime;

    private long amount;

    private long totalReceivedAmount;

    private List<ReceiveDto> receiveId;

}
