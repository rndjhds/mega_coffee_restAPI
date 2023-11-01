package com.megacoffee_jpa_restapi.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class InvalidMemberException extends RuntimeException{

    private Map<String, Object> errorMap;

    public InvalidMemberException(Map<String, Object> errorMap) {
        this.errorMap = errorMap;
    }
}
