package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.usecases.utils.NullEntityException;
import com.doo.sistemanutruco.usecases.utils.Validator;

public class DietaValidator implements Validator<Dieta> {
    public void validar(Dieta dieta) {
        if (dieta == null) {
            throw new NullEntityException("Dieta não pode ser nula");
        }
        if (dieta.getNome() == null || dieta.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da dieta não pode ser nulo ou vazio");
        }
        if (dieta.getObjetivo() == null || dieta.getObjetivo().isEmpty()) {
            throw new IllegalArgumentException("Objetivo da dieta não pode ser nulo ou vazio");
        }
    }
}
