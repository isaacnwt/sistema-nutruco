package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.utils.DAO;

import java.util.Optional;

public interface RefeicaoDAO extends DAO<Refeicao, Integer> {
    Optional<Refeicao> findByNome(String nome);
}
