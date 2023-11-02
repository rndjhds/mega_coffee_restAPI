package com.megacoffee_jpa_restapi.controller;

import com.megacoffee_jpa_restapi.exception.InvalidMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidMemberException.class)
    public Map<String, Object> validateException(InvalidMemberException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        e.getErrorMap().put("status", status);
        return e.getErrorMap();
    }
}
