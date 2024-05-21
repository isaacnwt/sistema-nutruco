package com.doo.sistemanutruco.entities.dia;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;

import java.util.List;

public class Dia {

    private Refeicao cafeManha;
    private Refeicao lancheManha;
    private Refeicao almoco;
    private Refeicao lancheTarde;
    private Refeicao jantar;
    private Refeicao ceia;
    private Boolean inativo;
    public Dia(Refeicao cafeManha, Refeicao lancheManha, Refeicao almoco, Refeicao lancheTarde, Refeicao jantar, Refeicao ceia, Boolean inativo) {
        this.cafeManha = cafeManha;
        this.lancheManha = lancheManha;
        this.almoco = almoco;
        this.lancheTarde = lancheTarde;
        this.jantar = jantar;
        this.ceia = ceia;
        this.inativo = inativo;
    }

    public Refeicao getCafeManha() {
        return cafeManha;
    }

    public void setCafeManha(Refeicao cafeManha) {
        this.cafeManha = cafeManha;
    }

    public Refeicao getLancheManha() {
        return lancheManha;
    }

    public void setLancheManha(Refeicao lancheManha) {
        this.lancheManha = lancheManha;
    }

    public Refeicao getAlmoco() {
        return almoco;
    }

    public void setAlmoco(Refeicao almoco) {
        this.almoco = almoco;
    }

    public Refeicao getLancheTarde() {
        return lancheTarde;
    }

    public void setLancheTarde(Refeicao lancheTarde) {
        this.lancheTarde = lancheTarde;
    }

    public Refeicao getJantar() {
        return jantar;
    }

    public void setJantar(Refeicao jantar) {
        this.jantar = jantar;
    }

    public Refeicao getCeia() {
        return ceia;
    }

    public void setCeia(Refeicao ceia) {
        this.ceia = ceia;
    }

    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

}
