package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.utils.EntityAlreadyExistsException;

public class CadastrarPacienteUseCase {

    private PacienteDAO pacienteDAO;

    public CadastrarPacienteUseCase(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public String cadastrar(Paciente paciente) {
        PacienteValidator pacienteValidator = new PacienteValidator();
        pacienteValidator.validar(paciente);

        if (pacienteDAO.findByCpf(paciente.getCpf()).isPresent())
            throw new EntityAlreadyExistsException("Paciente já cadastrado");

        return pacienteDAO.create(paciente);
    }
}
