package com.doo.sistemanutruco.usecases.dieta;

public class DesatribuirDietaUseCase {
    private final DietaDAO dietaDAO;

    public DesatribuirDietaUseCase(DietaDAO dietaDAO) {
        this.dietaDAO = dietaDAO;
    }

    public void desatribuirDietaDoPaciente(String pacienteCpf, int dietaId) {
        dietaDAO.removerDietaDoPaciente(pacienteCpf, dietaId);
    }
}
