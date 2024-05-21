package com.doo.sistemanutruco.usecases.alimento;

public class ImportarAlimentoUseCase {
    private final AlimentoDAO alimentoDAO;

    public ImportarAlimentoUseCase(AlimentoDAO alimentoDAO){
        this.alimentoDAO = alimentoDAO;
    }
}
