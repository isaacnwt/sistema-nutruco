package com.doo.sistemanutruco.entities.dieta;

import java.util.List;

public class Dieta {
    private Integer id;
    private String nome;
    private String objetivo;
    private List<Object> dias;
    private Boolean inativo;
    private Double calorias;
    private Double carboidratos;
    private Double proteinas;
    private Double sodio;
    private Boolean gluten;
    private Boolean lactose;
    private Double gorduras;

    public Dieta(String nome, String objetivo, List<Object> dias, Boolean inativo, Double calorias, Double carboidratos, Double proteinas, Double sodio, Boolean gluten, Boolean lactose, Double gorduras) {
        this.nome = nome;
        this.objetivo = objetivo;
        this.dias = dias;
        this.inativo = inativo;
        this.calorias = calorias;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.sodio = sodio;
        this.gluten = gluten;
        this.lactose = lactose;
        this.gorduras = gorduras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public List<Object> getDias() {
        return dias;
    }

    public void setDias(List<Object> dias) {
        this.dias = dias;
    }

    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public Double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(Double carboidratos) {
        this.carboidratos = carboidratos;
    }

    public Double getProteinas() {
        return proteinas;
    }

    public void setProteinas(Double proteinas) {
        this.proteinas = proteinas;
    }

    public Double getSodio() {
        return sodio;
    }

    public void setSodio(Double sodio) {
        this.sodio = sodio;
    }

    public Boolean getGluten() {
        return gluten;
    }

    public void setGluten(Boolean gluten) {
        this.gluten = gluten;
    }

    public Boolean getLactose() {
        return lactose;
    }

    public void setLactose(Boolean lactose) {
        this.lactose = lactose;
    }

    public Double getGorduras() {
        return gorduras;
    }

    public void setGorduras(Double gorduras) {
        this.gorduras = gorduras;
    }

}
