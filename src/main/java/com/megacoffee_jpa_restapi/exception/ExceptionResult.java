package com.megacoffee_jpa_restapi.exception;


import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public enum ExceptionResult {
    TOKEN_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "JWT 토큰의 시간이 만료되었습니다."),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");

    private HttpStatus httpStatus;
    private String status;
    private String code;
    private String message;

    ExceptionResult(HttpStatus httpStatus, String message) {
        String status = httpStatus.toString();
        List<String> list = Arrays.asList(status.split(" "));
        this.status = list.get(0);
        this.code = list.get(1);
        this.message = message;
    }

    public String getResult() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\"status\" :").append("\"" + status + "\"").append('\n');
        sb.append("\"code\" : ").append("\"" + code + "\"").append('\n');
        sb.append("\"message\" :").append("\"" + message + "\"").append('\n');
        return sb.toString();
    }
}
