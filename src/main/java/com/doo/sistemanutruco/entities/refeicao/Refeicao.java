package com.doo.sistemanutruco.entities.refeicao;

import com.doo.sistemanutruco.entities.alimento.Alimento;

import java.util.List;

public class Refeicao {
    private String nome;
    private String descricao;
    private String objetivo;
    private List<Alimento> alimentos;
    private Boolean contemGluten;
    private Boolean contemLactose;

    public Refeicao(String nome, String descricao, String objetivo, List<Alimento> alimentos, Boolean contemGluten, Boolean contemLactose) {
        this.nome = nome;
        this.descricao = descricao;
        this.objetivo = objetivo;
        this.alimentos = alimentos;
        this.contemGluten = contemGluten;
        this.contemLactose = contemLactose;
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

    public Boolean getContemGluten() {
        return contemGluten;
    }

    public void setContemGluten(Boolean contemGluten) {
        this.contemGluten = contemGluten;
    }

    public Boolean getContemLactose() {
        return contemLactose;
    }

    public void setContemLactose(Boolean contemLactose) {
        this.contemLactose = contemLactose;
    }

}
