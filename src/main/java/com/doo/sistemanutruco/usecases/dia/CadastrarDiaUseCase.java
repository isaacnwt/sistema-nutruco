package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;

public class CadastrarDiaUseCase {
    private final DiaDAO diaDAO;

    public CadastrarDiaUseCase(DiaDAO diaDAO) {
        this.diaDAO = diaDAO;
    }

    public Integer cadastrar(Dia dia) {
        // Adicionar validações

        return diaDAO.create(dia);
    }
}
