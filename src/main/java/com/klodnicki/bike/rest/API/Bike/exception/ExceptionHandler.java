package com.klodnicki.bike.rest.API.Bike.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundInDatabaseException.class)
    public ResponseEntity<RestException> handleNotFoundInDatabaseException(NotFoundInDatabaseException e) {
        return new ResponseEntity<>(new RestException("Nie ma takiego panie!", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
