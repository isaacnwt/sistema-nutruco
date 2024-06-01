package com.doo.sistemanutruco.main;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.memory.*;
import com.doo.sistemanutruco.usecases.alimento.AlimentoDAO;
import com.doo.sistemanutruco.usecases.alimento.ImportarAlimentoUseCase;
import com.doo.sistemanutruco.usecases.dia.CadastrarDiaUseCase;
import com.doo.sistemanutruco.usecases.dia.DiaDAO;
import com.doo.sistemanutruco.usecases.dia.EditarDiaUseCase;
import com.doo.sistemanutruco.usecases.dia.ExcluirDiaUseCase;
import com.doo.sistemanutruco.usecases.dieta.*;
import com.doo.sistemanutruco.usecases.paciente.*;
import com.doo.sistemanutruco.usecases.refeicao.CadastrarRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.EditarRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.ExcluirRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.RefeicaoDAO;

import java.time.LocalDate;

public class Main {

    private static CadastrarPacienteUseCase cadastrarPacienteUseCase;
    private static EditarPacienteUseCase editarPacienteUseCase;
    private static BuscarPacienteUseCase buscarPacienteUseCase;
    private static AtivarPacienteUseCase ativarPacienteUseCase;

    private static AtivarDietaUseCase ativarDietaUseCase;
    private static BuscarDietaUseCase buscarDietaUseCase;
    private static CadastrarDietaUseCase cadastrarDietaUseCase;
    private static ClonarDietaUseCase clonarDietaUseCase;
    private static ImportarAlimentoUseCase importarAlimentoUseCase;

    private static CadastrarRefeicaoUseCase cadastrarRefeicaoUseCase;
    private static EditarRefeicaoUseCase editarRefeicaoUseCase;
    private static ExcluirRefeicaoUseCase excluirRefeicaoUseCase;

    private static CadastrarDiaUseCase cadastrarDiaUseCase;
    private static EditarDiaUseCase editarDiaUseCase;
    private static ExcluirDiaUseCase excluirDiaUseCase;

    public static void main(String[] args) {
        // Paciente UseCases
        inicializarPacienteUseCases();

        // Dieta UseCases
        inicializarDietaUseCases();

        // Alimento UseCases
        inicializarAlimentoUseCases();

        // Refeicao UseCases
        inicializarRefeicaoUseCases();

        // Dia UseCases
        inicializarDiaUseCases();
    }

    private static void inicializarPacienteUseCases() {
        PacienteDAO pacienteDAO = new InMemoryPacienteDAO();
        cadastrarPacienteUseCase = new CadastrarPacienteUseCase(pacienteDAO);
        editarPacienteUseCase = new EditarPacienteUseCase(pacienteDAO);
        buscarPacienteUseCase = new BuscarPacienteUseCase(pacienteDAO);
        ativarPacienteUseCase = new AtivarPacienteUseCase(pacienteDAO);

        // Cadastrar Pacientes
        Paciente paciente1 = new Paciente("123", "teste", LocalDate.of(2000, 7, 2),
                16999998888L, "teste@email.com",90.0, 1.80, "ganhar massa");
        Paciente paciente2 = new Paciente("456", "teste2", LocalDate.of(2000, 8, 12),
                16999997777L, "teste2@email.com",80.0, 1.72, "emagrecer");

        cadastrarPacienteUseCase.cadastrar(paciente1);
        cadastrarPacienteUseCase.cadastrar(paciente2);

        // Buscar Pacientes
        buscarPacienteUseCase.findByCpf("123").ifPresent(System.out::println);
        buscarPacienteUseCase.findByCpf("456").ifPresent(System.out::println);

        // Editar Pacientes
        Paciente paciente1Update = new Paciente("123", "teste update", LocalDate.of(2000, 7, 2),
                16999990000L, "teste@email.com",90.0, 1.82, "ganhar massa");

        if (editarPacienteUseCase.editar(paciente1Update)) {
            System.out.println("Atualizado com sucesso");
            buscarPacienteUseCase.findByCpf("123").ifPresent(System.out::println);
        }
        else System.out.println("Erro ao atualizar");

        // Desativar/Ativar Paciente
        if (ativarPacienteUseCase.inativar(paciente2)) {
            System.out.println("Desativado com sucesso");
            buscarPacienteUseCase.findByCpf("456").ifPresent(System.out::println);
        }
        else System.out.println("Erro ao desativar");

        if (ativarPacienteUseCase.ativar(paciente2)) {
            System.out.println("Ativado com sucesso");
            buscarPacienteUseCase.findByCpf("456").ifPresent(System.out::println);
        }
        else System.out.println("Erro ao ativar");

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

    private static void inicializarDiaUseCases(){
        DiaDAO diaDAO = new InMemoryDiaDAO();
        cadastrarDiaUseCase = new CadastrarDiaUseCase(diaDAO);
        editarDiaUseCase = new EditarDiaUseCase(diaDAO);
        excluirDiaUseCase = new ExcluirDiaUseCase(diaDAO);
    }
}
