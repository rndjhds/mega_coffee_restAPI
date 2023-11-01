package com.megacoffee_jpa_restapi.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.megacoffee_jpa_restapi.exception.InvalidMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidMemberException.class)
    public Map<String, Object> validateException(InvalidMemberException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        e.getErrorMap().put("status", status);
        return e.getErrorMap();
    }

    /*@ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Map<String, Object>> tokenExpiredException() {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", "Token has expired.");
        return new ResponseEntity<>(errorResponse, status);
    }*/


}
