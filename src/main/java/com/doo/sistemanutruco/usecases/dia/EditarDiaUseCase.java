package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;

public class EditarDiaUseCase {
    private final DiaDAO diaDAO;

    public EditarDiaUseCase(DiaDAO diaDAO) {
        this.diaDAO = diaDAO;
    }

    public boolean editar(Dia dia){
        DiaValidator diaValidator = new DiaValidator();
        diaValidator.validar(dia);

        return diaDAO.update(dia);
    }
}
