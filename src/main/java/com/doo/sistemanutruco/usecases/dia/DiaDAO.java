package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.util.DAO;

import java.util.List;

public interface DiaDAO extends DAO<Dia, Integer> {
    void atribuirRefeicaoADia(Dia dia, Refeicao refeicao);

    List<Dia> findByDietaId(Integer dietaId);
}
