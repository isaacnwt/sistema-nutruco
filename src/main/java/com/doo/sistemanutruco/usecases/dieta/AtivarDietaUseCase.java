package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;

public class AtivarDietaUseCase {
    private final DietaDAO dietaDAO;

    public AtivarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }

    public boolean ativar(Dieta dieta){
        if (dieta.isAtiva())
            throw new IllegalStateException("Dieta já ativa!");

        dieta.setAtiva(true);
        return dietaDAO.update(dieta);
    }

    public boolean inativar(Dieta dieta){
        if (!dieta.isAtiva())
            throw new IllegalStateException("Dieta já inativa!");

        // TODO - validar
        dieta.setAtiva(false);
        return dietaDAO.update(dieta);
    }
}
