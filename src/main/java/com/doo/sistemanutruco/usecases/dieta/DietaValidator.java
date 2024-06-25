package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.usecases.utils.NullEntityException;
import com.doo.sistemanutruco.usecases.utils.Validator;

import java.time.LocalDate;

public class DietaValidator implements Validator<Dieta> {
    @Override
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
        if (dieta.getDataInicio() == null || dieta.getDataFim() == null) {
            throw new IllegalArgumentException("Datas de início e fim da dieta não podem ser nulas");
        }
        if (dieta.getDataInicio().isAfter(dieta.getDataFim())) {
            throw new IllegalArgumentException("A data de início da dieta não pode ser posterior à data de fim");
        }
        if (dieta.getDataFim().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de fim da dieta não pode ser anterior à data atual");
        }
    }
}
