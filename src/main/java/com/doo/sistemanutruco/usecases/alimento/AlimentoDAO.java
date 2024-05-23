package com.doo.sistemanutruco.usecases.alimento;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.usecases.utils.DAO;

import java.util.Optional;

public interface AlimentoDAO extends DAO<Alimento, Integer> {
    Optional<Alimento> findByNome(String nome);
}
