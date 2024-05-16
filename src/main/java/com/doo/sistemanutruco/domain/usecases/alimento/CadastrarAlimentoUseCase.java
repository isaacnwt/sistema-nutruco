package com.doo.sistemanutruco.domain.usecases.alimento;

public class CadastrarAlimentoUseCase {
    private final AlimentoDAO alimentoDAO;

    public CadastrarAlimentoUseCase(AlimentoDAO alimentoDAO){
        this.alimentoDAO = alimentoDAO;
    }
}
