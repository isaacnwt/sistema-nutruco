package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.repository.util.DAO;

import java.util.Optional;

public interface DietaDAO extends DAO<Dieta, Integer> {
    Optional<Dieta> findByNome(String name);
    Optional<Dieta> clone(Dieta dieta);
}