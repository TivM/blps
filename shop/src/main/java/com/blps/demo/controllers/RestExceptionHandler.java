package com.blps.demo.controllers;

import com.blps.demo.entity.controllers.error.ApiErrorResponse;
import com.blps.demo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        "Something not found or doesn't exist",
                        "0",
                        "Resource not found",
                        ex.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

}
