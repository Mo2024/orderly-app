package com.orderly.users.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleClassNotFound(ClassNotFoundException ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<>(
                CustomResponseCode.USER_NOT_FOUND.getCode(),
                CustomResponseCode.USER_NOT_FOUND.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidRequest(IllegalArgumentException ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<>(
                CustomResponseCode.INVALID_REQUEST.getCode(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(CustomException ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<>(
                ex.getErrorCode(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}