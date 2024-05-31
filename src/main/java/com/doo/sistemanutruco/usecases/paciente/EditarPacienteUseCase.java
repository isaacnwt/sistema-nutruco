package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.utils.EntityAlreadyExistsException;

public class EditarPacienteUseCase {
    private final PacienteDAO pacienteDAO;
    public EditarPacienteUseCase(PacienteDAO pacienteDAO){
        this.pacienteDAO = pacienteDAO;
    }

    public boolean editar(Paciente paciente){
        PacienteValidator pacienteValidator = new PacienteValidator();
        pacienteValidator.validar(paciente);

        return pacienteDAO.update(paciente);
    }
}
