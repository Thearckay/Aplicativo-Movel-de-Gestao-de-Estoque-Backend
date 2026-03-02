package com.thearckay.amge.controller;

import com.thearckay.amge.dto.response.ApiResponse;
import com.thearckay.amge.exceptions.UserLoginException;
import com.thearckay.amge.exceptions.UserRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    // registro
    // todo - dar mais detalhes do erro na resposta
    @ExceptionHandler(UserRegisterException.class)
    public ResponseEntity<ApiResponse<?>> registerError(UserRegisterException e){
        return ResponseEntity.status(e.getStatus())
                .body(new ApiResponse<>(
                        e.getStatus(),
                        e.getMessage(),
                        null
                ));
    }

    // login
    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<ApiResponse<?>> loginError (UserLoginException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(new ApiResponse<>(
                        e.getStatus(),
                        e.getMessage(),
                        null
                ));
    }
}
