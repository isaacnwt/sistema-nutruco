package com.doo.sistemanutruco.domain.usecases.alimento;

public class ImportarAlimentoUseCase {
    private final AlimentoDAO alimentoDAO;

    public ImportarAlimentoUseCase(AlimentoDAO alimentoDAO){
        this.alimentoDAO = alimentoDAO;
    }
}
