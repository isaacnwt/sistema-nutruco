package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;

public class AtivarPacienteUseCase {
    private final PacienteDAO pacienteDAO;

    public AtivarPacienteUseCase(PacienteDAO pacienteDAO){
        this.pacienteDAO = pacienteDAO;
    }

    public boolean ativar(Paciente paciente){
        if (!paciente.getInativo()){
            throw new IllegalStateException("Paciente já ativo!");
        }

        paciente.ativarPaciente();
        return pacienteDAO.update(paciente);
    }

    public boolean inativar(Paciente paciente){
        if (paciente.getInativo()){
            throw new IllegalStateException("Paciente já inativo!");
        }

        paciente.inativarPaciente();
        return pacienteDAO.update(paciente);
    }
}
