package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.dia.DiaDAO;

import java.util.List;

public class CadastrarRefeicoesUseCase {

    private final RefeicaoDAO refeicaoDAO;
    private final DiaDAO diaDAO;

    public CadastrarRefeicoesUseCase(RefeicaoDAO refeicaoDAO, DiaDAO diaDAO) {
        this.refeicaoDAO = refeicaoDAO;
        this.diaDAO = diaDAO;
    }

    public void cadastrarRefeicoesDosDias(List<Dia> dias) {
        for (Dia dia : dias) {
            for (Refeicao refeicao : dia.getRefeicoes()) {
                Integer refeicaoId = refeicaoDAO.create(refeicao);
                refeicao.setId(refeicaoId);
                diaDAO.atribuirRefeicaoADia(dia, refeicao);
            }
        }
    }
}
