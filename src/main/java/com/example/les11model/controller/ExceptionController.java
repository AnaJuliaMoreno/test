package com.example.les11model.controller;

import com.example.les11model.exception.DuplicateNameException;
import com.example.les11model.exception.NameTooLongException;
import com.example.les11model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<String> handleDuplicateNameException(DuplicateNameException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
        // another way to write the same error
        // HttpStatus.valueOf(409));
    }
    @ExceptionHandler(NameTooLongException.class)
    public ResponseEntity<String> handleNameTooLongException(NameTooLongException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(409));
    }
}
