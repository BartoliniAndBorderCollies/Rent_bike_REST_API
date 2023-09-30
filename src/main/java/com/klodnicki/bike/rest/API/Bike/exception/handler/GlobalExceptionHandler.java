package com.klodnicki.bike.rest.API.Bike.exception.handler;

import com.klodnicki.bike.rest.API.Bike.exception.UnauthorizedException;
import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundInDatabaseException.class)
    public ResponseEntity<RestException> handleNotFoundInDatabaseException(NotFoundInDatabaseException e) {
        return new ResponseEntity<>(new RestException("Nie ma takiego panie!", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestException> handleValidationRequirementNotMet(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new RestException("Validation error!", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
