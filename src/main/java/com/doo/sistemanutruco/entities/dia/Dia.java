package com.doo.sistemanutruco.entities.dia;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;

import java.time.DayOfWeek;
import java.util.List;

public class Dia {
    private Integer id = null;
    private DayOfWeek diaDaSemana;
    private List<Refeicao> refeicoes;
    private boolean inativo;
    private double caloriasNoDia;
    private double carboidratosNoDia;
    private double proteinasNoDia;
    private double sodioNoDia;
    private double gordurasNoDia;

    public Dia(DayOfWeek diaDaSemana, List<Refeicao> refeicoes) {
        this.diaDaSemana = diaDaSemana;
        this.refeicoes = refeicoes;
        calcularValoresNutricionais();
    }

    private void calcularValoresNutricionais() {
        for (Refeicao refeicao : refeicoes) {
            this.caloriasNoDia += refeicao.getCaloriasTotais();
            this.carboidratosNoDia += refeicao.getCarboidratosTotais();
            this.proteinasNoDia += refeicao.getProteinasTotais();
            this.sodioNoDia += refeicao.getSodioTotal();
            this.gordurasNoDia += refeicao.getGordurasTotais();
        }
    }

    public void ativar(){
        this.inativo = false;
    }

    public void inativar(){
        this.inativo = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DayOfWeek getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DayOfWeek diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }

    public boolean isInativo() {
        return inativo;
    }

    public double getCaloriasNoDia() {
        return caloriasNoDia;
    }

    public void setCaloriasNoDia(double caloriasNoDia) {
        this.caloriasNoDia = caloriasNoDia;
    }

    public double getCarboidratosNoDia() {
        return carboidratosNoDia;
    }

    public void setCarboidratosNoDia(double carboidratosNoDia) {
        this.carboidratosNoDia = carboidratosNoDia;
    }

    public double getProteinasNoDia() {
        return proteinasNoDia;
    }

    public void setProteinasNoDia(double proteinasNoDia) {
        this.proteinasNoDia = proteinasNoDia;
    }

    public double getSodioNoDia() {
        return sodioNoDia;
    }

    public void setSodioNoDia(double sodioNoDia) {
        this.sodioNoDia = sodioNoDia;
    }

    public double getGordurasNoDia() {
        return gordurasNoDia;
    }

    public void setGordurasNoDia(double gordurasNoDia) {
        this.gordurasNoDia = gordurasNoDia;
    }

    @Override
    public String toString() {
        return "DIA\n" +
                "id: " + id + "\n" +
                "diaDaSemana: " + diaDaSemana + "\n" +
                "refeicoes: " + refeicoes + "\n" +
                "inativo: " + inativo + "\n" +
                "caloriasNoDia: " + caloriasNoDia + "\n" +
                "carboidratosNoDia: " + carboidratosNoDia + "\n" +
                "proteinasNoDia: " + proteinasNoDia + "\n" +
                "sodioNoDia: " + sodioNoDia + "\n" +
                "gordurasNoDia: " + gordurasNoDia + "\n" ;
    }

}
