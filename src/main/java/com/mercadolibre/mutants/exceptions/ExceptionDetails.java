package com.mercadolibre.mutants.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ExceptionDetails {
    private HttpStatus status;
    private int statusCode;
    private String message;
}
