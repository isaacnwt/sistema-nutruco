package com.doo.sistemanutruco.main;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.memory.InMemoryAlimentoDAO;
import com.doo.sistemanutruco.repository.memory.InMemoryDietaDAO;
import com.doo.sistemanutruco.repository.memory.InMemoryPacienteDAO;
import com.doo.sistemanutruco.repository.memory.InMemoryRefeicaoDAO;
import com.doo.sistemanutruco.usecases.alimento.AlimentoDAO;
import com.doo.sistemanutruco.usecases.alimento.ImportarAlimentoUseCase;
import com.doo.sistemanutruco.usecases.dieta.*;
import com.doo.sistemanutruco.usecases.paciente.CadastrarPacienteUseCase;
import com.doo.sistemanutruco.usecases.paciente.PacienteDAO;
import com.doo.sistemanutruco.usecases.refeicao.CadastrarRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.EditarRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.ExcluirRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.RefeicaoDAO;

import java.time.LocalDate;
import java.util.Optional;

public class Main {

    private static CadastrarPacienteUseCase cadastrarPacienteUseCase;

    private static AtivarDietaUseCase ativarDietaUseCase;
    private static BuscarDietaUseCase buscarDietaUseCase;
    private static CadastrarDietaUseCase cadastrarDietaUseCase;
    private static ClonarDietaUseCase clonarDietaUseCase;
    private static ImportarAlimentoUseCase importarAlimentoUseCase;

    private static CadastrarRefeicaoUseCase cadastrarRefeicaoUseCase;
    private static EditarRefeicaoUseCase editarRefeicaoUseCase;
    private static ExcluirRefeicaoUseCase excluirRefeicaoUseCase;

    public static void main(String[] args) {
        // Paciente UseCases
        PacienteDAO pacienteDAO = new InMemoryPacienteDAO();
        cadastrarPacienteUseCase = new CadastrarPacienteUseCase(pacienteDAO);

        Paciente paciente1 = new Paciente("123", "teste", LocalDate.of(2000, 7, 2), 16999998888L,
                "teste@email.com",90.0, 1.80, "ganhar massa");

        cadastrarPacienteUseCase.cadastrar(paciente1);

        Optional<Paciente> pacienteOptional = pacienteDAO.findByCpf("123");
        pacienteOptional.ifPresent(System.out::println);

        // Dieta UseCases
        inicializarDietaUseCases();

        // Alimento UseCases
        inicializarAlimentoUseCases();

        // Refeicao UseCases
        inicializarRefeicaoUseCases();
    }

    private static void inicializarDietaUseCases(){
        DietaDAO dietaDAO = new InMemoryDietaDAO();
        ativarDietaUseCase = new AtivarDietaUseCase(dietaDAO);
        buscarDietaUseCase = new BuscarDietaUseCase(dietaDAO);
        cadastrarDietaUseCase = new CadastrarDietaUseCase(dietaDAO);
        clonarDietaUseCase = new ClonarDietaUseCase(dietaDAO);

        // Adicionar os testes ao finalizar as implementações das outras classes
    }

    private static void inicializarAlimentoUseCases(){
        AlimentoDAO alimentoDAO = new InMemoryAlimentoDAO();
        importarAlimentoUseCase = new ImportarAlimentoUseCase(alimentoDAO);
        // Adicionar os testes
    }

    private static void inicializarRefeicaoUseCases(){
        RefeicaoDAO refeicaoDAO = new InMemoryRefeicaoDAO();
        cadastrarRefeicaoUseCase = new CadastrarRefeicaoUseCase(refeicaoDAO);
        editarRefeicaoUseCase = new EditarRefeicaoUseCase(refeicaoDAO);
        excluirRefeicaoUseCase = new ExcluirRefeicaoUseCase(refeicaoDAO);
        // Adicionar os testes
    }
}
