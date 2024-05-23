package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;

import java.util.List;
import java.util.Optional;

public class BuscarDietaUseCase {
    private final DietaDAO dietaDAO;

    public BuscarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }

    public List<Dieta> findAll(){
        return dietaDAO.findAll();
    }

    public Optional<Dieta> findByNome(String nome){
        // Adicionar validações

        return dietaDAO.findByNome(nome);
    }
}
