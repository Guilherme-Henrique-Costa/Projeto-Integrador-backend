package com.example.voluntariadointeligentehub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {


    @NotBlank
    @Email
    private String email;


    @NotBlank
    private String senha;


    public LoginRequestDTO() {}


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
