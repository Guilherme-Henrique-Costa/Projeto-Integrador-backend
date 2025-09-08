package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admins", uniqueConstraints = @UniqueConstraint(name = "uk_admin_email", columnNames = "email"))
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 120)
    private String nome;


    @Column(nullable = false, length = 180, unique = true)
    private String email;


    @Column(nullable = false)
    private String senha; // hash (BCrypt)


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private AdminRole funcao = AdminRole.ADMIN_CEUB;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public AdminRole getFuncao() { return funcao; }
    public void setFuncao(AdminRole funcao) { this.funcao = funcao; }
}