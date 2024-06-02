package com.doo.sistemanutruco.entities.dia;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;

import java.time.DayOfWeek;
import java.util.List;

public class Dia {
    private DayOfWeek diaDaSemana;
    private List<Refeicao> refeicoes;
    private boolean inativo;

    public Dia(DayOfWeek diaDaSemana, List<Refeicao> refeicoes) {
        this.diaDaSemana = diaDaSemana;
        this.refeicoes = refeicoes;
    }

    public void ativar(){
        this.inativo = false;
    }

    public void inativar(){
        this.inativo = true;
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

    @Override
    public String toString() {
        return "Dia{" +
                "diaDaSemana=" + diaDaSemana +
                ", refeicoes=" + refeicoes +
                ", inativo=" + inativo +
                '}';
    }
}
