package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.utils.NullEntityException;
import com.doo.sistemanutruco.usecases.utils.Validator;

public class RefeicaoValidator implements Validator<Refeicao> {

    public void validar(Refeicao refeicao){
        if (refeicao == null){
            throw new NullEntityException("Refeição não pode ser nula");
        }
        if (refeicao.getNome() == null || refeicao.getNome().isEmpty()){
            throw new IllegalArgumentException("Nome da refeição não pode ser nulo ou vazio");
        }
        if (refeicao.getDescricao() == null || refeicao.getDescricao().isEmpty()){
            throw new IllegalArgumentException("Descrição da refeição não pode ser nulo ou vazio");
        }
        if (refeicao.getObjetivo() == null || refeicao.getObjetivo().isEmpty()){
            throw new IllegalArgumentException("Objetivo da refeição não pode ser nulo ou vazio");
        }
        if (refeicao.getAlimentos() == null || refeicao.getAlimentos().isEmpty()){
            throw new IllegalArgumentException("Lista de alimentos da refeição não podem ser nula ou vazia");
        }
    }
}
