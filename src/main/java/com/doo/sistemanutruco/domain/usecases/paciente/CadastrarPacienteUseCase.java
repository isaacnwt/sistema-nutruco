package com.doo.sistemanutruco.domain.usecases.paciente;

import com.doo.sistemanutruco.domain.entities.paciente.Paciente;
import com.doo.sistemanutruco.domain.usecases.utils.EntityAlreadyExistsException;

public class CadastrarPacienteUseCase {

    private PacienteDAO pacienteDAO;

    public CadastrarPacienteUseCase(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public String cadastrar(Paciente paciente) {
        if (pacienteDAO.findByCpf(paciente.getCpf()).isPresent())
            throw new EntityAlreadyExistsException("Paciente já cadastrado");
        return pacienteDAO.create(paciente);
    }
}
