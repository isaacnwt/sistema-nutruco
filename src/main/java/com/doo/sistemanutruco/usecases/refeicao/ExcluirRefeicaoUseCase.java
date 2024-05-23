package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;

public class ExcluirRefeicaoUseCase {
    private final RefeicaoDAO refeicaoDAO;

    public ExcluirRefeicaoUseCase(RefeicaoDAO refeicaoDAO){
        this.refeicaoDAO = refeicaoDAO;
    }

    public boolean excluir(Refeicao refeicao) {
        RefeicaoValidator refeicaoValidator = new RefeicaoValidator();
        refeicaoValidator.validar(refeicao);

        return refeicaoDAO.delete(refeicao);
    }
}
