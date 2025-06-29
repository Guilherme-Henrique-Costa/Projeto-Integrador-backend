package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_instituicao")
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(length = 60)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    @Column(length = 60)
    private String password;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String interestArea;
    private String telefoneContato;
    private String endereco;
    private String areaAtuacao;
    private String causasApoio;
    private String habilidadesRequeridas;
    private String responsavelPreenchimento;
    private String nomeContatoVoluntariado;
    private String funcaoContatoVoluntariado;
    private String telefoneContatoVoluntariado;
    private String semFinsLucrativos;
    private String constituidaFormalmente;
    private String emAtividade;
    private String sedeDesvinculada;
    private String prestadoraServicos;
    private String interesseRH;
    private String prestarInfosCEUB;
    private String avaliadaCEUB;
    private String motivoInteresseVoluntarios;
    private String enderecoTrabalhoVoluntario;
    private String horasMensaisVoluntario;
    private String contatosRepassadosVoluntarios;
    private String comentariosSugestoes;

    @OneToOne(mappedBy = "instituicao", cascade = CascadeType.ALL)
    private PerfilInstituicao perfilInstituicao;

    @OneToMany(mappedBy = "instituicao")
    private List<Voluntario> voluntarios;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getInterestArea() { return interestArea; }
    public void setInterestArea(String interestArea) { this.interestArea = interestArea; }

    public String getTelefoneContato() { return telefoneContato; }
    public void setTelefoneContato(String telefoneContato) { this.telefoneContato = telefoneContato; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getAreaAtuacao() { return areaAtuacao; }
    public void setAreaAtuacao(String areaAtuacao) { this.areaAtuacao = areaAtuacao; }

    public String getCausasApoio() { return causasApoio; }
    public void setCausasApoio(String causasApoio) { this.causasApoio = causasApoio; }

    public String getHabilidadesRequeridas() { return habilidadesRequeridas; }
    public void setHabilidadesRequeridas(String habilidadesRequeridas) { this.habilidadesRequeridas = habilidadesRequeridas; }

    public String getResponsavelPreenchimento() { return responsavelPreenchimento; }
    public void setResponsavelPreenchimento(String responsavelPreenchimento) { this.responsavelPreenchimento = responsavelPreenchimento; }

    public String getNomeContatoVoluntariado() { return nomeContatoVoluntariado; }
    public void setNomeContatoVoluntariado(String nomeContatoVoluntariado) { this.nomeContatoVoluntariado = nomeContatoVoluntariado; }

    public String getFuncaoContatoVoluntariado() { return funcaoContatoVoluntariado; }
    public void setFuncaoContatoVoluntariado(String funcaoContatoVoluntariado) { this.funcaoContatoVoluntariado = funcaoContatoVoluntariado; }

    public String getTelefoneContatoVoluntariado() { return telefoneContatoVoluntariado; }
    public void setTelefoneContatoVoluntariado(String telefoneContatoVoluntariado) { this.telefoneContatoVoluntariado = telefoneContatoVoluntariado; }

    public String getSemFinsLucrativos() { return semFinsLucrativos; }
    public void setSemFinsLucrativos(String semFinsLucrativos) { this.semFinsLucrativos = semFinsLucrativos; }

    public String getConstituidaFormalmente() { return constituidaFormalmente; }
    public void setConstituidaFormalmente(String constituidaFormalmente) { this.constituidaFormalmente = constituidaFormalmente; }

    public String getEmAtividade() { return emAtividade; }
    public void setEmAtividade(String emAtividade) { this.emAtividade = emAtividade; }

    public String getSedeDesvinculada() { return sedeDesvinculada; }
    public void setSedeDesvinculada(String sedeDesvinculada) { this.sedeDesvinculada = sedeDesvinculada; }

    public String getPrestadoraServicos() { return prestadoraServicos; }
    public void setPrestadoraServicos(String prestadoraServicos) { this.prestadoraServicos = prestadoraServicos; }

    public String getInteresseRH() { return interesseRH; }
    public void setInteresseRH(String interesseRH) { this.interesseRH = interesseRH; }

    public String getPrestarInfosCEUB() { return prestarInfosCEUB; }
    public void setPrestarInfosCEUB(String prestarInfosCEUB) { this.prestarInfosCEUB = prestarInfosCEUB; }

    public String getAvaliadaCEUB() { return avaliadaCEUB; }
    public void setAvaliadaCEUB(String avaliadaCEUB) { this.avaliadaCEUB = avaliadaCEUB; }

    public String getMotivoInteresseVoluntarios() { return motivoInteresseVoluntarios; }
    public void setMotivoInteresseVoluntarios(String motivoInteresseVoluntarios) { this.motivoInteresseVoluntarios = motivoInteresseVoluntarios; }

    public String getEnderecoTrabalhoVoluntario() { return enderecoTrabalhoVoluntario; }
    public void setEnderecoTrabalhoVoluntario(String enderecoTrabalhoVoluntario) { this.enderecoTrabalhoVoluntario = enderecoTrabalhoVoluntario; }

    public String getHorasMensaisVoluntario() { return horasMensaisVoluntario; }
    public void setHorasMensaisVoluntario(String horasMensaisVoluntario) { this.horasMensaisVoluntario = horasMensaisVoluntario; }

    public String getContatosRepassadosVoluntarios() { return contatosRepassadosVoluntarios; }
    public void setContatosRepassadosVoluntarios(String contatosRepassadosVoluntarios) { this.contatosRepassadosVoluntarios = contatosRepassadosVoluntarios; }

    public String getComentariosSugestoes() { return comentariosSugestoes; }
    public void setComentariosSugestoes(String comentariosSugestoes) { this.comentariosSugestoes = comentariosSugestoes; }

    public PerfilInstituicao getPerfilInstituicao() { return perfilInstituicao; }
    public void setPerfilInstituicao(PerfilInstituicao perfilInstituicao) { this.perfilInstituicao = perfilInstituicao; }

    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }
}
