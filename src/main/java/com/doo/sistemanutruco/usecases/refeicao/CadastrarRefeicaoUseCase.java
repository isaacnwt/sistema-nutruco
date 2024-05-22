package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.utils.EntityAlreadyExistsException;

public class CadastrarRefeicaoUseCase {
    private final RefeicaoDAO refeicaoDAO;
    
    public CadastrarRefeicaoUseCase(RefeicaoDAO refeicaoDAO){
        this.refeicaoDAO = refeicaoDAO;
    }

    public String cadastrar(Refeicao refeicao){
        RefeicaoValidator validator = new RefeicaoValidator();
        validator.validar(refeicao);

        if (refeicaoDAO.findByNome(refeicao.getNome()).isPresent()){
            throw new EntityAlreadyExistsException("Refeição com mesmo nome já cadastrada");
        }

        return refeicaoDAO.create(refeicao);
    }
}
