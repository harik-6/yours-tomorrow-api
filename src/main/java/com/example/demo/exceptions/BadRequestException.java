package com.example.demo.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private Integer statusCode = 400;
    public BadRequestException(String message) {
        super(message);
    }
}
