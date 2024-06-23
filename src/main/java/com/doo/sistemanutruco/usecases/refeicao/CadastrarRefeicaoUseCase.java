package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;

public class CadastrarRefeicaoUseCase {
    private final RefeicaoDAO refeicaoDAO;
    
    public CadastrarRefeicaoUseCase(RefeicaoDAO refeicaoDAO){
        this.refeicaoDAO = refeicaoDAO;
    }

    public Integer cadastrar(Refeicao refeicao){
        RefeicaoValidator validator = new RefeicaoValidator();
        validator.validar(refeicao);

        refeicaoDAO.findByNome(refeicao.getNome()).ifPresent(v -> {
            throw new IllegalStateException("Refeição com mesmo nome já cadastrada!");
        });

        return refeicaoDAO.create(refeicao);
    }
}
