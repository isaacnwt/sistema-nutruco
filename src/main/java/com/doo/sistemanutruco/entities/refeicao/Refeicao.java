package com.doo.sistemanutruco.entities.refeicao;

import com.doo.sistemanutruco.entities.alimento.Alimento;

import java.util.List;

public class Refeicao {
    private Integer id = null;
    private String nome;
    private String descricao;
    private String objetivo;
    private List<Alimento> alimentos;
    private boolean contemGluten;
    private boolean contemLactose;
    private double caloriasTotais;
    private double carboidratosTotais;
    private double proteinasTotais;
    private double sodioTotal;
    private double gordurasTotais;

    public Refeicao(String nome, String descricao, String objetivo, List<Alimento> alimentos) {
        this.nome = nome;
        this.descricao = descricao;
        this.objetivo = objetivo;
        this.alimentos = alimentos;
        calcularValoresNutricionais();
    }

    private void calcularValoresNutricionais() {
        for (Alimento alimento : alimentos) {
            this.caloriasTotais += alimento.getCalorias();
            this.carboidratosTotais += alimento.getCarboidratos();
            this.proteinasTotais += alimento.getProteinas();
            this.sodioTotal += alimento.getSodio();
            this.gordurasTotais += alimento.getGorduras();
            if (alimento.getGluten()) this.contemGluten = true;
            if (alimento.getLactose()) this.contemLactose = true;
        }
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public boolean getContemGluten() {
        return contemGluten;
    }

    public void setContemGluten(boolean contemGluten) {
        this.contemGluten = contemGluten;
    }

    public boolean getContemLactose() {
        return contemLactose;
    }

    public void setContemLactose(boolean contemLactose) {
        this.contemLactose = contemLactose;
    }

    public double getCaloriasTotais() {
        return caloriasTotais;
    }

    public void setCaloriasTotais(double caloriasTotais) {
        this.caloriasTotais = caloriasTotais;
    }

    public double getCarboidratosTotais() {
        return carboidratosTotais;
    }

    public void setCarboidratosTotais(double carboidratosTotais) {
        this.carboidratosTotais = carboidratosTotais;
    }

    public double getProteinasTotais() {
        return proteinasTotais;
    }

    public void setProteinasTotais(double proteinasTotais) {
        this.proteinasTotais = proteinasTotais;
    }

    public double getSodioTotal() {
        return sodioTotal;
    }

    public void setSodioTotal(double sodioTotal) {
        this.sodioTotal = sodioTotal;
    }

    public double getGordurasTotais() {
        return gordurasTotais;
    }

    public void setGordurasTotais(double gordurasTotais) {
        this.gordurasTotais = gordurasTotais;
    }

    @Override
    public String toString() {
        return "Refeicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", alimentos=" + alimentos +
                ", contemGluten=" + contemGluten +
                ", contemLactose=" + contemLactose +
                ", caloriasTotais=" + caloriasTotais +
                ", carboidratosTotais=" + carboidratosTotais +
                ", proteinasTotais=" + proteinasTotais +
                ", sodioTotal=" + sodioTotal +
                ", gordurasTotais=" + gordurasTotais +
                '}';
    }
}
