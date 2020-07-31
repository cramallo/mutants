package com.mercadolibre.mutants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public final ResponseEntity<ExceptionDetails> handleUserNotFoundException(final BadRequestException badRequest, final WebRequest request) {
        final ExceptionDetails errorDetails = ExceptionDetails.builder()
                .message(badRequest.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InternalServerException.class})
    public final ResponseEntity<ExceptionDetails> handleServerErrorException(final InternalServerException serverError, final WebRequest request) {
        final ExceptionDetails errorDetails = ExceptionDetails.builder()
                .message(serverError.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
