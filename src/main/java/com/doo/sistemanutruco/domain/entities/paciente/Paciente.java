package com.doo.sistemanutruco.domain.entities.paciente;

import java.util.Date;

public class Paciente {
    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getPorcentagemGordura() {
        return porcentagemGordura;
    }

    public void setPorcentagemGordura(Integer porcentagemGordura) {
        this.porcentagemGordura = porcentagemGordura;
    }

    public Integer getPorcentagemMassaMagra() {
        return porcentagemMassaMagra;
    }

    public void setPorcentagemMassaMagra(Integer porcentagemMassaMagra) {
        this.porcentagemMassaMagra = porcentagemMassaMagra;
    }

    public Integer getPorcentagemMassaGorda() {
        return porcentagemMassaGorda;
    }

    public void setPorcentagemMassaGorda(Integer porcentagemMassaGorda) {
        this.porcentagemMassaGorda = porcentagemMassaGorda;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getColesterolLDL() {
        return colesterolLDL;
    }

    public void setColesterolLDL(Integer colesterolLDL) {
        this.colesterolLDL = colesterolLDL;
    }

    public Integer getColesterolHDL() {
        return colesterolHDL;
    }

    public void setColesterolHDL(Integer colesterolHDL) {
        this.colesterolHDL = colesterolHDL;
    }

    public Boolean getHipertenso() {
        return hipertenso;
    }

    public void setHipertenso(Boolean hipertenso) {
        this.hipertenso = hipertenso;
    }

    public Boolean getDiabetico() {
        return diabetico;
    }

    public void setDiabetico(Boolean diabetico) {
        this.diabetico = diabetico;
    }

    public Boolean getCeliaco() {
        return celiaco;
    }

    public void setCeliaco(Boolean celiaco) {
        this.celiaco = celiaco;
    }

    public Paciente(Boolean inativo, String cpf, String nome, Date dataNascimento, Integer telefone, String email, Double altura, String objetivo, Integer porcentagemGordura, Integer porcentagemMassaMagra, Integer porcentagemMassaGorda, Double peso, Integer colesterolLDL, Integer colesterolHDL, Boolean hipertenso, Boolean diabetico, Boolean celiaco) {
        this.inativo = inativo;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.altura = altura;
        this.objetivo = objetivo;
        this.porcentagemGordura = porcentagemGordura;
        this.porcentagemMassaMagra = porcentagemMassaMagra;
        this.porcentagemMassaGorda = porcentagemMassaGorda;
        this.peso = peso;
        this.colesterolLDL = colesterolLDL;
        this.colesterolHDL = colesterolHDL;
        this.hipertenso = hipertenso;
        this.diabetico = diabetico;
        this.celiaco = celiaco;
    }

    public void ativarPaciente(){
        this.inativo = false;
    }
    
    public void inativarPaciente(){
        this.inativo = true;
    }

    //    private Dieta dieta;
    private Boolean inativo;
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private Integer telefone;
    private String email;
    private Double altura;
    private String objetivo;
    private Integer porcentagemGordura;
    private Integer porcentagemMassaMagra;
    private Integer porcentagemMassaGorda;
    private Double peso;
    private Integer colesterolLDL;
    private Integer colesterolHDL;
    private Boolean hipertenso;
    private Boolean diabetico;
    private Boolean celiaco;
}
