package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.paciente.PacienteDAO;
import com.doo.sistemanutruco.usecases.utils.EntityNotFoundException;


import java.util.List;

public class AtribuirDietaUseCase {

    private final PacienteDAO pacienteDAO;
    private final DietaDAO dietaDAO;

    public AtribuirDietaUseCase(PacienteDAO pacienteDAO, DietaDAO dietaDAO) {
        this.pacienteDAO = pacienteDAO;
        this.dietaDAO = dietaDAO;
    }

    public Integer atribuirDieta(Paciente paciente, Dieta dieta) {
        DietaValidator dietaValidator = new DietaValidator();
        dietaValidator.validar(dieta);

        pacienteDAO.findByCpf(paciente.getCpf()).orElseThrow(() -> new EntityNotFoundException("Paciente não cadastrado"));

        List<Dieta> dietasDoPaciente = dietaDAO.findByPaciente(paciente);

        for (Dieta dietaExistente : dietasDoPaciente) {
            if (!dietaExistente.isInativo() &&
                    dietaExistente.getDataInicio().isBefore(dieta.getDataFim()) &&
                    dietaExistente.getDataFim().isAfter(dieta.getDataInicio())) {
                throw new IllegalStateException("Paciente já possui uma dieta ativa nesse período");
            }
        }

        Integer dietaId = dietaDAO.create(dieta);
        dieta.setId(dietaId);
        pacienteDAO.atribuirDietaAPaciente(dieta, paciente);

        return dietaId;
    }
}
