package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;

import java.util.Optional;

public class ClonarDietaUseCase {
    private final DietaDAO dietaDAO;

    public ClonarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }

    public Optional<Dieta> clonarDieta(Dieta dieta){
        DietaValidator dietaValidator = new DietaValidator();
        dietaValidator.validar(dieta);

        return dietaDAO.clone(dieta);
    }
}
