package com.spring.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandel {
    @ExceptionHandler(NoUserFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(NoUserFound ex) {
        ErrorResponse eObject = new ErrorResponse();
        eObject.setStatus(HttpStatus.OK.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(eObject, HttpStatus.OK);
    }
}
