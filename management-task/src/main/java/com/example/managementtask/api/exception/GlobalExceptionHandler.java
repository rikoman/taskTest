package com.example.managementtask.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleBadRequestException(HttpServletRequest request, BadRequestException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST);
        body.put("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path",request.getServletPath());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Map<String,Object>> handleConflict(HttpServletRequest request,RuntimeException ex) {
        Map<String,Object> body = new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST);
        body.put("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path",request.getServletPath());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handlelValidationException(HttpServletRequest request,MethodArgumentNotValidException ex) {
        Map<String,Object> body = new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST);
        body.put("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path",request.getServletPath());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,Object>> handleValidationsException(HttpServletRequest request,ConstraintViolationException ex) {
        Map<String,Object> body = new HashMap<>();

        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String errorMessage = violation.getPropertyPath() + " " + violation.getMessage();
            errors.add(errorMessage);
        }

        body.put("status",HttpStatus.BAD_REQUEST);
        body.put("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", errors);
        body.put("path",request.getServletPath());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handlelssValidationException(HttpServletRequest request,HttpMessageNotReadableException ex) {
        Map<String,Object> body = new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST);
        body.put("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path",request.getServletPath());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}