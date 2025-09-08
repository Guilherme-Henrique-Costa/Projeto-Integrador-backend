package com.example.voluntariadointeligentehub.dto;


public class AdminLoginResponseDTO {
    private String token;
    private Long id;
    private String nome;
    private String papel; // ADMIN | ADMIN_CEUB (opcional)


    public AdminLoginResponseDTO() {}


    public AdminLoginResponseDTO(String token, Long id, String nome, String papel) {
        this.token = token; this.id = id; this.nome = nome; this.papel = papel;
    }


    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPapel() { return papel; }
    public void setPapel(String papel) { this.papel = papel; }
}