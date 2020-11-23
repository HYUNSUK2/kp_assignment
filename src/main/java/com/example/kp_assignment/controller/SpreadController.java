package com.example.kp_assignment.controller;

import com.example.kp_assignment.common.CommonResponse;
import com.example.kp_assignment.common.HttpCode;
import com.example.kp_assignment.dto.SpreadDto;
import com.example.kp_assignment.service.ScatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/spread")
public class SpreadController {

    @Autowired
    private ScatterService spreadService;

    @PostMapping
    public ResponseEntity<Object> spreadMoney(
            @RequestHeader("X-USER-ID") long userId,
            @RequestHeader("X-ROOM-ID") String roomId,
            @RequestBody SpreadDto spreadDto) {
        CommonResponse response = CommonResponse.builder()
                .status(HttpCode.OK)
                .message("성공")
                .data(spreadService.spreadMoney(userId, roomId, spreadDto))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
