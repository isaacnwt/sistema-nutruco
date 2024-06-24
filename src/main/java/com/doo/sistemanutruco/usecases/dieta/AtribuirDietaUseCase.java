package com.doo.sistemanutruco.usecases.dieta;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.dia.DiaDAO;
import com.doo.sistemanutruco.usecases.paciente.PacienteDAO;
import com.doo.sistemanutruco.usecases.refeicao.RefeicaoDAO;
import com.doo.sistemanutruco.usecases.utils.EntityNotFoundException;

import java.util.List;

public class AtribuirDietaUseCase {
    private final PacienteDAO pacienteDAO;
    private final DietaDAO dietaDAO;
    private final DiaDAO diaDAO;
    private final RefeicaoDAO refeicaoDAO;

    public AtribuirDietaUseCase(PacienteDAO pacienteDAO, DietaDAO dietaDAO, DiaDAO diaDAO, RefeicaoDAO refeicaoDAO) {
        this.pacienteDAO = pacienteDAO;
        this.dietaDAO = dietaDAO;
        this.diaDAO = diaDAO;
        this.refeicaoDAO = refeicaoDAO;
    }

    public Integer atribuirDieta(Paciente paciente, Dieta dieta) {
        if (!pacienteDAO.findByCpf(paciente.getCpf()).isPresent()) {
            throw new EntityNotFoundException("Paciente não cadastrado");
        }

        List<Dieta> dietasDoPaciente = dietaDAO.findByPaciente(paciente);

        for (Dieta dietaExistente : dietasDoPaciente) {
            if (!dietaExistente.isInativo() &&
                    dietaExistente.getDataInicio().isBefore(dieta.getDataFim()) &&
                    dietaExistente.getDataFim().isAfter(dieta.getDataInicio())) {
                throw new IllegalStateException("Paciente já possui uma dieta ativa nesse período");
            }
        }

        dietaDAO.create(dieta);

        for (Dia dia : dieta.getDias()) {
            diaDAO.create(dia);

            for (Refeicao refeicao : dia.getRefeicoes()) {
                refeicaoDAO.create(refeicao);
                diaDAO.atribuirRefeicaoADia(dia, refeicao);
            }

            dietaDAO.atribuirDiaADieta(dieta, dia);
        }

        return dieta.getId();
    }
}
