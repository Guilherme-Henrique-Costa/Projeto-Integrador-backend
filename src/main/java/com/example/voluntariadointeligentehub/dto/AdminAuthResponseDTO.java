package com.example.voluntariadointeligentehub.dto;

public class AdminAuthResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String funcao;
    private String token; // usado no /authenticate (opcional no /register)
    private String message; // feedback amigável


    public AdminAuthResponseDTO() {}


    public AdminAuthResponseDTO(Long id, String nome, String email, String funcao, String token, String message) {
        this.id = id; this.nome = nome; this.email = email; this.funcao = funcao; this.token = token; this.message = message;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}