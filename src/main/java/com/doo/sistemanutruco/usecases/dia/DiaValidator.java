package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.usecases.utils.NullEntityException;
import com.doo.sistemanutruco.usecases.utils.Validator;

public class DiaValidator implements Validator<Dia> {
    @Override
    public void validar(Dia dia) {
        if (dia == null) {
            throw new NullEntityException("Dieta não pode ser nula");
        }
        if (dia.getCafeManha() == null) {
            throw new NullEntityException("Cafe da manhã da dieta não pode ser nulo");
        }
        if (dia.getLancheManha() == null) {
            throw new NullEntityException("Lanche da manhã da dieta não pode ser nulo");
        }
        if (dia.getAlmoco() == null) {
            throw new NullEntityException("Almoço da dieta não pode ser nulo");
        }
        if (dia.getLancheTarde() == null) {
            throw new NullEntityException("Lanhce da tarde da dieta não pode ser nulo");
        }
        if (dia.getJantar() == null) {
            throw new NullEntityException("Jantar da dieta não pode ser nulo");
        }
    }
}
