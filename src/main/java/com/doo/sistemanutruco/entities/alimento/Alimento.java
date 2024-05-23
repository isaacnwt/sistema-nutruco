package com.doo.sistemanutruco.entities.alimento;

public class Alimento {
    private Integer id = null;
    private String nome;
    private Double calorias;
    private Double carboidratos;
    private Double proteinas;
    private Double sodio;
    private Boolean gluten;
    private Boolean lactose;
    private Double gorduras;

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

    public Alimento(String nome, Double calorias, Double carboidratos, Double proteinas, Double sodio, Boolean gluten, Boolean lactose, Double gorduras) {
        this.nome = nome;
        this.calorias = calorias;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.sodio = sodio;
        this.gluten = gluten;
        this.lactose = lactose;
        this.gorduras = gorduras;
    }
}
