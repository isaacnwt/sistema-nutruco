package com.doo.sistemanutruco.usecases.dia;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
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

    public void cadastrarDiasDaDieta(Dieta dieta) {
        List<Dia> dias = new ArrayList<>();
        for (DayOfWeek diaDaSemana : DayOfWeek.values()) {
            Dia dia = new Dia(diaDaSemana, new ArrayList<>());
            Integer diaId = diaDAO.create(dia);
            dia.setId(diaId);
            dias.add(dia);
            dietaDAO.atribuirDiaADieta(dieta, dia);
        }
    }
}
