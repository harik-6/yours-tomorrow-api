package com.example.demo.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppException {
    private String message;
    @JsonProperty("status_code")
    private Integer statusCode;
}
