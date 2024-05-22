package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.usecases.utils.NullEntityException;

public class DietaValidator {
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
        if (dieta.getDias() == null || dieta.getDias().isEmpty()) {
            throw new IllegalArgumentException("Dias da dieta não podem ser nulos");
        }
        if (dieta.getCalorias() == null || dieta.getCalorias().isNaN()) {
            throw new IllegalArgumentException("Calorias da dieta não podem ser nulas");
        }
        if (dieta.getCarboidratos() == null || dieta.getCarboidratos().isNaN()) {
            throw new IllegalArgumentException("Carboídratos da dieta não podem ser nulos");
        }
        if (dieta.getProteinas() == null || dieta.getProteinas().isNaN()) {
            throw new IllegalArgumentException("Proteínas da dieta não podem ser nulas");
        }
        if (dieta.getSodio() == null || dieta.getSodio().isNaN()) {
            throw new IllegalArgumentException("Sódio da dieta não pode ser nulo");
        }
        if (dieta.getGluten() == null) {
            throw new IllegalArgumentException("Glúten da dieta não pode ser nulo");
        }
        if (dieta.getLactose() == null) {
            throw new IllegalArgumentException("Lactose da dieta não pode ser nulo");
        }
    }
}
