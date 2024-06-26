package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.dieta.DietaDAO;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CadastrarDiaUseCase {

    private final DiaDAO diaDAO;
    private final DietaDAO dietaDAO;

    public CadastrarDiaUseCase(DiaDAO diaDAO, DietaDAO dietaDAO) {
        this.diaDAO = diaDAO;
        this.dietaDAO = dietaDAO;
    }

    public List<Dia> cadastrarDiasDaDieta(Integer dietaId, List<Refeicao> refeicoes) {
        List<Dia> dias = new ArrayList<>();
        for (DayOfWeek diaDaSemana : DayOfWeek.values()) {
            Dia dia = new Dia(diaDaSemana, refeicoes);
            Integer diaId = diaDAO.create(dia);
            dia.setId(diaId);
            dietaDAO.atribuirDiaADieta(dietaId, dia);
            dias.add(dia);
        }
        return dias;
    }
}
