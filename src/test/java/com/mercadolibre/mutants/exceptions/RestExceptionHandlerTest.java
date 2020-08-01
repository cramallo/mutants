package com.mercadolibre.mutants.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestExceptionHandlerTest {

    @Test
    public void whenForbiddenExceptionThenReturnResponseEntity() {
        final RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        final ExceptionDetails errorDetailsMock = ExceptionDetails.builder()
                .message("forbidden")
                .status(HttpStatus.FORBIDDEN)
                .statusCode(HttpStatus.FORBIDDEN.value())
                .build();

        final ResponseEntity mockResponseEntity = new ResponseEntity<>(errorDetailsMock, HttpStatus.FORBIDDEN);

        final ResponseEntity responseEntity = restExceptionHandler.handleForbiddenException(new ForbiddenException("forbidden"));
        assertEquals(mockResponseEntity, responseEntity);
    }

    @Test
    public void whenInternalServerExceptionThenReturnResponseEntity() {
        final RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        final ExceptionDetails errorDetailsMock = ExceptionDetails.builder()
                .message("internal server error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        final ResponseEntity mockResponseEntity = new ResponseEntity<>(errorDetailsMock, HttpStatus.INTERNAL_SERVER_ERROR);

        final ResponseEntity responseEntity = restExceptionHandler.handleServerErrorException(new InternalServerException("internal server error"));
        assertEquals(mockResponseEntity, responseEntity);
    }

    @Test
    public void whenBadRequestExceptionThenReturnResponseEntity() {
        final RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        final ExceptionDetails errorDetailsMock = ExceptionDetails.builder()
                .message("bad request")
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        final ResponseEntity mockResponseEntity = new ResponseEntity<>(errorDetailsMock, HttpStatus.BAD_REQUEST);

        final ResponseEntity responseEntity = restExceptionHandler.handleBadRequestException(new BadRequestException("bad request"));
        assertEquals(mockResponseEntity, responseEntity);
    }


}
