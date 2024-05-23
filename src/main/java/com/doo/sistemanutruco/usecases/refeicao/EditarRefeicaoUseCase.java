package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;

public class EditarRefeicaoUseCase {
    private final RefeicaoDAO refeicaoDAO;

    public EditarRefeicaoUseCase(RefeicaoDAO refeicaoDAO){
        this.refeicaoDAO = refeicaoDAO;
    }

    public Boolean editar(Refeicao refeicao){
        RefeicaoValidator validator = new RefeicaoValidator();
        validator.validar(refeicao);

        return refeicaoDAO.update(refeicao);
    }
}
