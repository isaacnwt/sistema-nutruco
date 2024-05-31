package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;

public class VisualizarProgressoPacienteUseCase {

    private PacienteDAO pacienteDAO;

    public VisualizarProgressoPacienteUseCase(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public void gerarInformacoesProgressoPaciente(Paciente paciente){
        PacienteValidator pacienteValidator = new PacienteValidator();
        pacienteValidator.validar(paciente);

        System.out.println(paciente);
    }
}
