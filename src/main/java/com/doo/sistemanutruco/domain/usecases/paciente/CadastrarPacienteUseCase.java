package com.doo.sistemanutruco.domain.usecases.paciente;

import com.doo.sistemanutruco.domain.entities.paciente.Paciente;

public class CadastrarPacienteUseCase {

    private PacienteDAO pacienteDAO;

    public CadastrarPacienteUseCase(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public String cadastrar(Paciente paciente) {
        return pacienteDAO.create(paciente);
    }
}
