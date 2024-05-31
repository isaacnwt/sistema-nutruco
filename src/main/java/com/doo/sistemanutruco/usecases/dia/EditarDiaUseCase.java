package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;

public class EditarDiaUseCase {
    private final DiaDAO diaDAO;

    public EditarDiaUseCase(DiaDAO diaDAO) {
        this.diaDAO = diaDAO;
    }

    public boolean editar(Dia dia){
        // Adicionar validações

        return diaDAO.update(dia);
    }
}
