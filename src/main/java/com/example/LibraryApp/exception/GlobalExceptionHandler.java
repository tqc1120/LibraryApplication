package com.example.LibraryApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage());
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(DuplicateResourceException.class)
//    public ResponseEntity<?> handleDuplicateResourceException(DuplicateResourceException ex) {
//        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage());
//        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGlobalException(Exception ex) {
//        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage());
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
