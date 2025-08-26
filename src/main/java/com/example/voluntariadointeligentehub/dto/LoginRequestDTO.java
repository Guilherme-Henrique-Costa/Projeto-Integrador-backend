package com.example.voluntariadointeligentehub.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String senha;

    public LoginRequestDTO() {}
}
