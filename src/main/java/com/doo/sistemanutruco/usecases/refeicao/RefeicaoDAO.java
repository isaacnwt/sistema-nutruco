package com.doo.sistemanutruco.usecases.refeicao;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.util.DAO;

import java.util.List;
import java.util.Optional;

public interface RefeicaoDAO extends DAO<Refeicao, Integer> {
    Optional<Refeicao> findByNome(String nome);
    List<Refeicao> findByDiaId(Integer id);
}
