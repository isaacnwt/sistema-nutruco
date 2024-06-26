package com.doo.sistemanutruco.usecases.dieta;

public class RemoverDietaUseCase {
    private final DietaDAO dietaDAO;

    public RemoverDietaUseCase(DietaDAO dietaDAO) {
        this.dietaDAO = dietaDAO;
    }

    public void removerDietaDoPaciente(String pacienteCpf, int dietaId) {
        dietaDAO.removerDietaDoPaciente(pacienteCpf, dietaId);
    }
}
