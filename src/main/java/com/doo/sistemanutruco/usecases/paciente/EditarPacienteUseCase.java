package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;

public class EditarPacienteUseCase {
    private final PacienteDAO pacienteDAO;
    public EditarPacienteUseCase(PacienteDAO pacienteDAO){
        this.pacienteDAO = pacienteDAO;
    }

    public boolean editar(Paciente paciente){
        // Adicionar validações

        return pacienteDAO.update(paciente);
    }
}
