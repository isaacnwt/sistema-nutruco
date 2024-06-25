package com.doo.sistemanutruco.entities.dieta;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;

import java.time.LocalDate;
import java.util.List;

public class Dieta {
    private Integer id = null;
    private String nome;
    private String objetivo;
    private List<Dia> dias;
    private boolean inativo;
    private double caloriasDaDieta;
    private double carboidratosDaDieta;
    private double proteinasDaDieta;
    private double sodioDaDieta;
    private boolean dietaContemGluten;
    private boolean dietaContemLactose;
    private double gordurasDaDieta;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Dieta() {
    }

    public Dieta(String nome, String objetivo, List<Dia> dias, LocalDate dataInicio, LocalDate dataFim) {
        this.nome = nome;
        this.objetivo = objetivo;
        this.dias = dias;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        calcularValoresNutricionais();
    }

    private void calcularValoresNutricionais() {
        for (Dia dia : this.dias) {
            this.caloriasDaDieta += dia.getCaloriasNoDia();
            this.carboidratosDaDieta += dia.getCarboidratosNoDia();
            this.proteinasDaDieta += dia.getProteinasNoDia();
            this.sodioDaDieta += dia.getSodioNoDia();
            this.gordurasDaDieta += dia.getGordurasNoDia();
            var refeicoesComGlutem = dia.getRefeicoes().stream().filter(Refeicao::getContemGluten).toList();
            var refeicoesComLactose = dia.getRefeicoes().stream().filter(Refeicao::getContemLactose).toList();
            if (!refeicoesComGlutem.isEmpty()) this.dietaContemGluten = true;
            if (!refeicoesComLactose.isEmpty()) this.dietaContemLactose = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dieta dieta = (Dieta) o;
        return id == dieta.id;
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

    public List<Dia> getDias() {
        return dias;
    }

    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }

    public boolean isInativo() {
        return this.inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public double getCaloriasDaDieta() {
        return caloriasDaDieta;
    }

    public void setCaloriasDaDieta(double caloriasDaDieta) {
        this.caloriasDaDieta = caloriasDaDieta;
    }

    public double getCarboidratosDaDieta() {
        return carboidratosDaDieta;
    }

    public void setCarboidratosDaDieta(double carboidratosDaDieta) {
        this.carboidratosDaDieta = carboidratosDaDieta;
    }

    public double getProteinasDaDieta() {
        return proteinasDaDieta;
    }

    public void setProteinasDaDieta(double proteinasDaDieta) {
        this.proteinasDaDieta = proteinasDaDieta;
    }

    public double getSodioDaDieta() {
        return sodioDaDieta;
    }

    public void setSodioDaDieta(double sodioDaDieta) {
        this.sodioDaDieta = sodioDaDieta;
    }

    public boolean getDietaContemGluten() {
        return dietaContemGluten;
    }

    public void setDietaContemGluten(boolean dietaContemGluten) {
        this.dietaContemGluten = dietaContemGluten;
    }

    public boolean getDietaContemLactose() {
        return dietaContemLactose;
    }

    public void setDietaContemLactose(boolean dietaContemLactose) {
        this.dietaContemLactose = dietaContemLactose;
    }

    public double getGordurasDaDieta() {
        return gordurasDaDieta;
    }

    public void setGordurasDaDieta(double gordurasDaDieta) {
        this.gordurasDaDieta = gordurasDaDieta;
    }

    public boolean isDietaContemGluten() {
        return dietaContemGluten;
    }

    public boolean isDietaContemLactose() {
        return dietaContemLactose;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Dieta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", dias=" + dias +
                ", inativo=" + inativo +
                ", caloriasDaDieta=" + caloriasDaDieta +
                ", carboidratosDaDieta=" + carboidratosDaDieta +
                ", proteinasDaDieta=" + proteinasDaDieta +
                ", sodioDaDieta=" + sodioDaDieta +
                ", dietaContemGluten=" + dietaContemGluten +
                ", dietaContemLactose=" + dietaContemLactose +
                ", gordurasDaDieta=" + gordurasDaDieta +
                '}';
    }
}
