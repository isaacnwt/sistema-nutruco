package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;

public class ExcluirDiaUseCase {
    private final DiaDAO diaDAO;

    public ExcluirDiaUseCase(DiaDAO diaDAO) {
        this.diaDAO = diaDAO;
    }

    public boolean excluir(Dia dia) {
        DiaValidator diaValidator = new DiaValidator();
        diaValidator.validar(dia);

        return diaDAO.delete(dia);
    }
}
