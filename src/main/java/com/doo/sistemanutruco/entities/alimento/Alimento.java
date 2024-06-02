package com.doo.sistemanutruco.entities.alimento;

public class Alimento {
    private Integer id = null;
    private String nome;
    private Double calorias;
    private Double carboidratos;
    private Double proteinas;
    private Double sodio;
    private boolean gluten;
    private boolean lactose;
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

    public boolean getGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public boolean getLactose() {
        return lactose;
    }

    public void setLactose(boolean lactose) {
        this.lactose = lactose;
    }

    public Double getGorduras() {
        return gorduras;
    }

    public void setGorduras(Double gorduras) {
        this.gorduras = gorduras;
    }

    public Alimento(String nome, Double calorias, Double carboidratos, Double proteinas, Double sodio, boolean gluten, boolean lactose, Double gorduras) {
        this.nome = nome;
        this.calorias = calorias;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.sodio = sodio;
        this.gluten = gluten;
        this.lactose = lactose;
        this.gorduras = gorduras;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", calorias=" + calorias +
                ", carboidratos=" + carboidratos +
                ", proteinas=" + proteinas +
                ", sodio=" + sodio +
                ", gluten=" + gluten +
                ", lactose=" + lactose +
                ", gorduras=" + gorduras +
                '}';
    }
}
