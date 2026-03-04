package com.thearckay.amge.controller;

import com.thearckay.amge.dto.response.ApiResponse;
import com.thearckay.amge.exceptions.RegisterItemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemControllerAdvice {

    @ExceptionHandler(RegisterItemException.class)
    public ResponseEntity<ApiResponse<Void>> handleInventoryException(RegisterItemException ex) {
        ApiResponse<Void> response = new ApiResponse<>(
                ex.getStatus(),
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(ex.getStatus()).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        ApiResponse<Void> response = new ApiResponse<>(
                500,
                "Erro interno no servidor: ",
                null
        );
        return ResponseEntity.status(500).body(response);
    }
}
