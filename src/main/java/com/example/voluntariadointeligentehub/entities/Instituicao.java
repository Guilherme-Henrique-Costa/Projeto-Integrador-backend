package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
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
    private String senha;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    // Contatos/descrição
    private String interestArea;
    private String telefoneContato;
    private String description;

    // Endereço (alinhado ao Angular)
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    // Seleções (arrays) - alinhar com Angular [areaAtuacao, causasApoio, habilidadesRequeridas]
    @ElementCollection
    @CollectionTable(name = "tb_inst_area_atuacao", joinColumns = @JoinColumn(name = "instituicao_id"))
    @Column(name = "area")
    private List<String> areaAtuacao = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tb_inst_causas", joinColumns = @JoinColumn(name = "instituicao_id"))
    @Column(name = "causa")
    private List<String> causasApoio = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tb_inst_habs", joinColumns = @JoinColumn(name = "instituicao_id"))
    @Column(name = "habilidade")
    private List<String> habilidadesRequeridas = new ArrayList<>();

    // Demais campos
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

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getInterestArea() { return interestArea; }
    public void setInterestArea(String interestArea) { this.interestArea = interestArea; }

    public String getTelefoneContato() { return telefoneContato; }
    public void setTelefoneContato(String telefoneContato) { this.telefoneContato = telefoneContato; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    public List<String> getAreaAtuacao() { return areaAtuacao; }
    public void setAreaAtuacao(List<String> areaAtuacao) { this.areaAtuacao = areaAtuacao; }

    public List<String> getCausasApoio() { return causasApoio; }
    public void setCausasApoio(List<String> causasApoio) { this.causasApoio = causasApoio; }

    public List<String> getHabilidadesRequeridas() { return habilidadesRequeridas; }
    public void setHabilidadesRequeridas(List<String> habilidadesRequeridas) { this.habilidadesRequeridas = habilidadesRequeridas; }

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

    public List<Voluntario> getVoluntarios() { return voluntarios; }
    public void setVoluntarios(List<Voluntario> voluntarios) { this.voluntarios = voluntarios; }
}
