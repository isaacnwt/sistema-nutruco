package com.doo.sistemanutruco.usecases.alimento;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.repository.util.DAO;

import java.util.Optional;

public interface AlimentoDAO extends DAO<Alimento, Integer> {
    Optional<Alimento> findByNome(String nome);
}
