package com.example.demo.exceptions;

import lombok.Data;

@Data
public class RecordNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private Integer statusCode = 400;
    public RecordNotFoundException(String message) {
        super(message);
    }
}
