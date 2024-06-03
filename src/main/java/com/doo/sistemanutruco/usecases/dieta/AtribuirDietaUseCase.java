package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.paciente.PacienteDAO;

public class AtribuirDietaUseCase {
    private final PacienteDAO pacienteDAO;

    public AtribuirDietaUseCase(PacienteDAO pacienteDAO) {this.pacienteDAO = pacienteDAO;}

    public boolean atribuirDieta(Paciente paciente, Dieta dieta){
        paciente.addDieta(dieta);
        return  pacienteDAO.update(paciente);
    }

    public boolean desatribuirDieta(Paciente paciente, Dieta dieta) {
        paciente.desatribuirDieta(dieta);
        return pacienteDAO.update(paciente);
    }

}
