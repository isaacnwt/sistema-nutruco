package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.util.DAO;

import java.util.Optional;

public interface PacienteDAO extends DAO<Paciente, String> {
    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByNome(String nome);
    void atribuirDietaAPaciente(Dieta dieta, Paciente paciente);
}
