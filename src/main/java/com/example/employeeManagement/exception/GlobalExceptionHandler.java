package com.example.employeeManagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(EmployeeNotFoundException exception, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("currenttime", ZonedDateTime.now());
        response.put("errormessage", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(InvalidInputException exception, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("currenttime", ZonedDateTime.now());
        response.put("errormessage", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(DatabaseException exception, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("currenttime", ZonedDateTime.now());
        response.put("errormessage", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleResourceNotFoundException(Exception exception, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("currenttime", ZonedDateTime.now());
        response.put("errormessage", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
