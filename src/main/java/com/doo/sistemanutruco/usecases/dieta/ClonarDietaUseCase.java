package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;

import java.util.Optional;

public class ClonarDietaUseCase {
    private final DietaDAO dietaDAO;

    public ClonarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }

    public Dieta clonarDieta(Dieta dieta){
        DietaValidator dietaValidator = new DietaValidator();
        dietaValidator.validar(dieta);

        Optional<Dieta> dietaBase = dietaDAO.findByNome(dieta.getNome());
        Dieta dietaOriginal = dietaBase.get();
        return dietaOriginal.clonarDieta();
    }
}
