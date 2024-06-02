package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.usecases.utils.NullEntityException;
import com.doo.sistemanutruco.usecases.utils.Validator;

public class DiaValidator implements Validator<Dia> {
    @Override
    public void validar(Dia dia) {
        if (dia == null) {
            throw new NullEntityException("Dia não pode ser nulo");
        }
        if (dia.getDiaDaSemana() == null) {
            throw new IllegalArgumentException("Dia da semana do Dia não pode ser nulo");
        }
        if (dia.getRefeicoes() == null || dia.getRefeicoes().isEmpty()) {
            throw new IllegalArgumentException("Lista de Refeições não pode ser nulo ou vazia");
        }
        if (dia.getRefeicoes().size() > 6) {
            throw new IllegalArgumentException("Lista de Refeições pode conter no máximo 6 refeições");
        }
    }
}
