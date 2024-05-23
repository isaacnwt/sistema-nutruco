package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface PacienteDAO extends DAO<Paciente, String> {
    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByNome(String nome);
}
