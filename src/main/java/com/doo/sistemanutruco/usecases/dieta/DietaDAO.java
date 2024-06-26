package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.util.DAO;

import java.util.List;

public interface DietaDAO extends DAO<Dieta, Integer> {
    List<Dieta> findByPaciente(Paciente paciente);
    void atribuirDiaADieta(Integer dietaId, Dia dia);
}