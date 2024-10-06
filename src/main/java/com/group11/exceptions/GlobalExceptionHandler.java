package com.group11.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception exception, WebRequest req){
        ErrorDetails error = new ErrorDetails(exception.getMessage(),req.getDescription(false),LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
    }
}
