package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;

public class AtivarDietaUseCase {
    private final DietaDAO dietaDAO;

    public AtivarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }

    public boolean ativar(Dieta dieta){
        DietaValidator dietaValidator = new DietaValidator();
        dietaValidator.validar(dieta);

        if (!dieta.getInativo()){
            throw new IllegalStateException("Dieta já ativa!");
        }
        dieta.setInativo(false);
        return dietaDAO.update(dieta);
    }

    public boolean inativar(Dieta dieta){
        DietaValidator dietaValidator = new DietaValidator();
        dietaValidator.validar(dieta);

        if (dieta.getInativo()){
            throw new IllegalStateException("Dieta já inativa!");
        }
        // Adicionar validação se a dieta estiver em uso (CDU006 - Fluxo Alt 1)
        dieta.setInativo(true);
        return dietaDAO.update(dieta);
    }
}
