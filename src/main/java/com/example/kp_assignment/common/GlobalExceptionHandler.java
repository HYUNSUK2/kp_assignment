package com.example.kp_assignment.common;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    HttpCode httpCode;
    HttpStatus httpStatus;
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity handlerException(GlobalException e) {
        String[] message = e.getMessage().split("::");
        String errorMessage = message[0].strip();
        String code = message[1].strip();

        if (code.equals("400")){
            httpCode = HttpCode.BAD_REQUEST;
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (code.equals("500")){
            httpCode = HttpCode.INTERNAL_SERER_ERROR;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (code.equals("404")){
            httpCode = HttpCode.NOT_FOUND;
            httpStatus = HttpStatus.NOT_FOUND;
        }

        CommonResponse response = CommonResponse.builder()
                .status(httpCode)
                .message(errorMessage)
                .data(null)
                .build();
        return new ResponseEntity(response, httpStatus);
    }
}
