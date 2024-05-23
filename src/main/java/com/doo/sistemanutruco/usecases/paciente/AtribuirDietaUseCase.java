package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.usecases.dieta.DietaDAO;

public class AtribuirDietaUseCase {
    private final PacienteDAO pacienteDAO;

    public AtribuirDietaUseCase(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;

    }

    public void atribuirDieta(DietaDAO dieta){
        // Adicionar validações
    }

}
