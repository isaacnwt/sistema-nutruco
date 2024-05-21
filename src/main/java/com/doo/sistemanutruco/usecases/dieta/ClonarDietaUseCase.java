package com.doo.sistemanutruco.usecases.dieta;

public class ClonarDietaUseCase {
    private final DietaDAO dietaDAO;

    public ClonarDietaUseCase(DietaDAO dietaDAO){
        this.dietaDAO = dietaDAO;
    }
}
