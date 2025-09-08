package com.example.voluntariadointeligentehub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminRegisterRequestDTO {
    @NotBlank
    @Size(min = 3)
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;
    private String funcao; // ADMIN_CEUB | ADMIN (opcional)


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }
}