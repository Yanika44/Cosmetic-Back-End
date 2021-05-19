package com.example.cosmetic2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductsExceptions.class)
    public ResponseEntity<Object> handleExceptions(ProductsExceptions exception) {
        ExceptionsResponse response =
                new ExceptionsResponse(exception.getErrorCode(),exception.getMessage(), LocalDateTime.now());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }
}
