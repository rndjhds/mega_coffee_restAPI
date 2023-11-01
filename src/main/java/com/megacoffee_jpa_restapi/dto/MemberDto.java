package com.megacoffee_jpa_restapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberDto {
    @NotBlank(message = "ID값을 입력해주세요")
    private String id;
    @NotBlank(message = "PASSWORD값을 입력해주세요")
    private String password;
    @NotBlank(message = "USERNAME값을 입력해주세요")
    private String username;
    @NotBlank(message = "EMAIL값을 입력해주세요")
    private String email;
}
