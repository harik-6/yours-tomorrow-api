package com.example.demo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private Integer statusCode = 400;
    public BadRequestException(String message) {
        super(message);
    }
}
