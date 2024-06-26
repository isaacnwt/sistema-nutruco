package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;

import java.util.List;

import static java.util.Objects.isNull;

public class BuscarDietasDoPacienteUseCase {

    private final DietaDAO dietaDAO;

    public BuscarDietasDoPacienteUseCase(DietaDAO dietaDAO) {
        this.dietaDAO = dietaDAO;
    }

    public List<Dieta> buscarDietas(Paciente paciente) {
        if (isNull(paciente))  throw new IllegalArgumentException("Paciente não pode ser nulo");
        return dietaDAO.findByPaciente(paciente);
    }
}
