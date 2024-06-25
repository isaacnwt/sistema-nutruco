package com.doo.sistemanutruco.usecases.alimento;

import com.doo.sistemanutruco.entities.alimento.Alimento;

import java.util.List;

public class ListarAlimentosUseCase {
    private final AlimentoDAO alimentoDAO;

    public ListarAlimentosUseCase(AlimentoDAO alimentoDAO){
        this.alimentoDAO = alimentoDAO;
    }

    public List<Alimento> listarAlimentos() {
        return alimentoDAO.findAll();
    }
}
