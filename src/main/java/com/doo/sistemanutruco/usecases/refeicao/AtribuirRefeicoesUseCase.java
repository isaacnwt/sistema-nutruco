package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.dia.DiaDAO;

import java.util.List;

public class AtribuirRefeicoesUseCase {

    private final DiaDAO diaDAO;

    public AtribuirRefeicoesUseCase(DiaDAO diaDAO) {
        this.diaDAO = diaDAO;
    }

    public void atribuirRefeicoesDosDias(List<Dia> dias) {
        for (Dia dia : dias)
            for (Refeicao refeicao : dia.getRefeicoes())
                diaDAO.atribuirRefeicaoADia(dia, refeicao);
    }
}
