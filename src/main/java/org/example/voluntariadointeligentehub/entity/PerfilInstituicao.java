package org.example.voluntariadointeligentehub.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "instituicoes")
public class PerfilInstituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 caracteres")
    private String cnpj;

    @Column(nullable = false)
    @NotBlank(message = "A área de atuação é obrigatória")
    private String areaAtuacao;

    @Column(nullable = false)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;  // A senha pode ser criptografada no serviço

    @Column(length = 250)
    @JsonProperty("description") // Mapeia "description" no JSON para "descricao"
    private String descricao;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getAreaAtuacao() { return areaAtuacao; }
    public void setAreaAtuacao(String areaAtuacao) { this.areaAtuacao = areaAtuacao; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
