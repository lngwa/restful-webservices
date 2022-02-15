package com.lewis.in28minsrestfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomExceptionResponseEntity extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(), 8001);
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundExceptionException(UserNotFoundException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(), 8004);
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> handlePostNotFoundExceptionException(PostNotFoundException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(), 8004);
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(), 8009);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
