package com.example.demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<AppException> handleBadRequestException(BadRequestException ex){
        AppException exception = new AppException(ex.getMessage().toLowerCase(),ex.getStatusCode());
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<AppException> handleOtherException(Exception ex){
        AppException exception = new AppException(ex.getMessage().toLowerCase(),500);
        return ResponseEntity.internalServerError().body(exception);
    }
}
