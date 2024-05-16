package com.doo.sistemanutruco.domain.usecases.paciente;

import com.doo.sistemanutruco.domain.entities.paciente.Paciente;
import com.doo.sistemanutruco.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface PacienteDAO extends DAO<Paciente, String> {
    Optional<Paciente> findByCpf(String cpf);
    List<Paciente> findByNome(String nome);
}
