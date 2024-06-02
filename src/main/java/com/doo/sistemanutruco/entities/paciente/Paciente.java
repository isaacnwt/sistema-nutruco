package com.doo.sistemanutruco.entities.paciente;

import com.doo.sistemanutruco.entities.dieta.Dieta;

import java.time.LocalDate;
import java.util.List;


public class Paciente {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Long telefone;
    private String email;
    private Double peso;
    private Double altura;
    private String objetivo;

    private Integer porcentagemGordura;
    private Integer porcentagemMassaMagra;
    private Integer porcentagemMassaGorda;
    private Integer colesterolLDL;
    private Integer colesterolHDL;
    private boolean hipertenso;
    private boolean diabetico;
    private boolean celiaco;

    private List<Dieta> dietas;
    private boolean inativo;


    public Paciente(String cpf, String nome, LocalDate dataNascimento, Long telefone, String email, Double peso, Double altura, String objetivo) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
    }

    public Paciente(String cpf, String nome, LocalDate dataNascimento, Long telefone, String email, Double peso, Double altura, String objetivo, Integer porcentagemGordura, Integer porcentagemMassaMagra, Integer porcentagemMassaGorda, Integer colesterolLDL, Integer colesterolHDL, boolean hipertenso, boolean diabetico, boolean celiaco) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.porcentagemGordura = porcentagemGordura;
        this.porcentagemMassaMagra = porcentagemMassaMagra;
        this.porcentagemMassaGorda = porcentagemMassaGorda;
        this.colesterolLDL = colesterolLDL;
        this.colesterolHDL = colesterolHDL;
        this.hipertenso = hipertenso;
        this.diabetico = diabetico;
        this.celiaco = celiaco;
    }

    public void ativar(){
        this.inativo = false;
    }

    public void inativar(){
        this.inativo = true;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
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

    public boolean getHipertenso() {
        return hipertenso;
    }

    public void setHipertenso(boolean hipertenso) {
        this.hipertenso = hipertenso;
    }

    public boolean getDiabetico() {
        return diabetico;
    }

    public void setDiabetico(boolean diabetico) {
        this.diabetico = diabetico;
    }

    public boolean getCeliaco() {
        return celiaco;
    }

    public void setCeliaco(boolean celiaco) {
        this.celiaco = celiaco;
    }

    public List<Dieta> getDieta() {
        return dietas;
    }

    public void setDieta(List<Dieta> dietas) {
        this.dietas = dietas;
    }
    public void addDieta(Dieta dieta){
        this.dietas.add(dieta);
    }

    public boolean getInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    @Override
    public String toString() {
        return "PACIENTE\n" +
                "cpf: '" + cpf + '\'' + "\n" +
                "nome: '" + nome + '\'' + "\n" +
                "dataNascimento: " + dataNascimento + "\n" +
                "telefone: " + telefone + "\n" +
                "email: '" + email + '\'' + "\n" +
                "peso: " + peso + "\n" +
                "altura: " + altura + "\n" +
                "objetivo: '" + objetivo + '\'' + "\n" +
                "inativo: " + inativo + "\n" ;
    }

}
