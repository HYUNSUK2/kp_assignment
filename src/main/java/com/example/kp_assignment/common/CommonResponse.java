package com.example.kp_assignment.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonResponse {
    private HttpCode status;
    private String message;
    private Object data;

}
