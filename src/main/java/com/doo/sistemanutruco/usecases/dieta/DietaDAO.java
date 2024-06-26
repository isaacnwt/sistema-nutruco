package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.util.DAO;

import java.util.List;
import java.util.Optional;

public interface DietaDAO extends DAO<Dieta, Integer> {
    List<Dieta> findByPaciente(Paciente paciente);
    Optional<Dieta> clone(Dieta dieta, List<Dieta> todasDietasDoPaciente);
    void atribuirDiaADieta(Integer dietaId, Dia dia);
    void removerDietaDoPaciente(String pacienteCpf, Integer dietaId);
}