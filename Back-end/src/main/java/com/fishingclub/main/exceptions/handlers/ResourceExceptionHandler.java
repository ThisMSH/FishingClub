package com.fishingclub.main.exceptions.handlers;

import com.fishingclub.main.exceptions.ResourceAlreadyExistException;
import com.fishingclub.main.exceptions.ResourceBadRequestException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.exceptions.ResourceUnprocessableException;
import com.fishingclub.main.utils.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseHandler.exception(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceBadRequestException.class})
    public ResponseEntity<Object> handleResourceBadRequestException(ResourceBadRequestException e) {
        return ResponseHandler.exception(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {
        return ResponseHandler.exception(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ResourceUnprocessableException.class})
    public ResponseEntity<Object> handleResourceUnprocessableException(ResourceUnprocessableException e) {
        return ResponseHandler.exception(e, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
