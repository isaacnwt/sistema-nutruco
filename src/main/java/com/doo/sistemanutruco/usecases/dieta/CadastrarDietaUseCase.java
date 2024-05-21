package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;

public class CadastrarDietaUseCase {
    private final DietaDAO dietaDAO;

    public CadastrarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }

    public String cadastrar(Dieta dieta){
        // Adicionar validações

        return dietaDAO.create(dieta);
    }
}
