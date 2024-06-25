package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
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

    public void cadastrarRefeicoesDosDias(Dieta dieta, List<Refeicao> refeicoes) {
        for (Dia dia : dieta.getDias()) {
            for (Refeicao refeicao : refeicoes) {
                Integer refeicaoId = refeicaoDAO.create(refeicao);
                refeicao.setId(refeicaoId);
                diaDAO.atribuirRefeicaoADia(dia, refeicao);
            }
        }
    }
}
