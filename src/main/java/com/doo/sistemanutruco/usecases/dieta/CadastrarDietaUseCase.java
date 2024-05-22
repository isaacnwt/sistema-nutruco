package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.usecases.utils.EntityAlreadyExistsException;

public class CadastrarDietaUseCase {
    private final DietaDAO dietaDAO;

    public CadastrarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }

    public String cadastrar(Dieta dieta){
        DietaValidator dietaValidator = new DietaValidator();
        dietaValidator.validar(dieta);

        if (dietaDAO.findByNome(dieta.getNome()).isPresent()){
            throw new EntityAlreadyExistsException("Dieta com mesmo nome já registrada");
        }

        return dietaDAO.create(dieta);
    }
}
