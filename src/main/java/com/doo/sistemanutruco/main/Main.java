package com.doo.sistemanutruco.main;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.memory.*;
import com.doo.sistemanutruco.usecases.alimento.AlimentoDAO;
import com.doo.sistemanutruco.usecases.alimento.ImportarAlimentosUseCase;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static CadastrarPacienteUseCase cadastrarPacienteUseCase;
    private static EditarPacienteUseCase editarPacienteUseCase;
    private static BuscarPacienteUseCase buscarPacienteUseCase;
    private static AtivarPacienteUseCase ativarPacienteUseCase;

    private static ImportarAlimentosUseCase importarAlimentoUseCase;

    private static CadastrarRefeicaoUseCase cadastrarRefeicaoUseCase;
    private static EditarRefeicaoUseCase editarRefeicaoUseCase;
    private static ExcluirRefeicaoUseCase excluirRefeicaoUseCase;

    private static CadastrarDiaUseCase cadastrarDiaUseCase;
    private static EditarDiaUseCase editarDiaUseCase;
    private static ExcluirDiaUseCase excluirDiaUseCase;

    private static AtivarDietaUseCase ativarDietaUseCase;
    private static BuscarDietaUseCase buscarDietaUseCase;
    private static CadastrarDietaUseCase cadastrarDietaUseCase;
    private static ClonarDietaUseCase clonarDietaUseCase;

    public static void main(String[] args) {
        // Inicialização dos UseCases
        PacienteDAO pacienteDAO = new InMemoryPacienteDAO();
        AlimentoDAO alimentoDAO = new InMemoryAlimentoDAO();
        RefeicaoDAO refeicaoDAO = new InMemoryRefeicaoDAO();
        DiaDAO diaDAO = new InMemoryDiaDAO();
        DietaDAO dietaDAO = new InMemoryDietaDAO();
        inicializarPacienteUseCases(pacienteDAO);
        inicializarAlimentoUseCases(alimentoDAO);
        inicializarRefeicaoUseCases(refeicaoDAO);
        inicializarDiaUseCases(diaDAO);
        inicializarDietaUseCases(dietaDAO);


        System.out.println("Paciente Use Cases:\n");
        inicializarTestesPaciente();

        System.out.println("Criar Alimentos, Refeicoes e Dia Use Cases:\n");
        inicializarTestesDeCriacao(alimentoDAO, refeicaoDAO, diaDAO, dietaDAO);

        System.out.println("Dieta Use Cases:\n");
        inicializarTestesDieta(dietaDAO);
    }

    private static void inicializarPacienteUseCases(PacienteDAO pacienteDAO) {
        cadastrarPacienteUseCase = new CadastrarPacienteUseCase(pacienteDAO);
        editarPacienteUseCase = new EditarPacienteUseCase(pacienteDAO);
        buscarPacienteUseCase = new BuscarPacienteUseCase(pacienteDAO);
        ativarPacienteUseCase = new AtivarPacienteUseCase(pacienteDAO);
    }

    private static void inicializarAlimentoUseCases(AlimentoDAO alimentoDAO){
        importarAlimentoUseCase = new ImportarAlimentosUseCase(alimentoDAO);
    }

    private static void inicializarRefeicaoUseCases(RefeicaoDAO refeicaoDAO){
        cadastrarRefeicaoUseCase = new CadastrarRefeicaoUseCase(refeicaoDAO);
        editarRefeicaoUseCase = new EditarRefeicaoUseCase(refeicaoDAO);
        excluirRefeicaoUseCase = new ExcluirRefeicaoUseCase(refeicaoDAO);
    }

    private static void inicializarDiaUseCases(DiaDAO diaDAO){
        cadastrarDiaUseCase = new CadastrarDiaUseCase(diaDAO);
        editarDiaUseCase = new EditarDiaUseCase(diaDAO);
        excluirDiaUseCase = new ExcluirDiaUseCase(diaDAO);
    }

    private static void inicializarDietaUseCases(DietaDAO dietaDAO){
        ativarDietaUseCase = new AtivarDietaUseCase(dietaDAO);
        buscarDietaUseCase = new BuscarDietaUseCase(dietaDAO);
        cadastrarDietaUseCase = new CadastrarDietaUseCase(dietaDAO);
        clonarDietaUseCase = new ClonarDietaUseCase(dietaDAO);
    }

    private static void inicializarTestesPaciente(){
        // CDU001 – Cadastrar paciente
        System.out.println("Cadastrar Paciente/Buscar Paciente");
        Paciente paciente1 = new Paciente("123", "teste", LocalDate.of(2000, 7, 2),
                16999998888L, "teste@email.com",90.0, 1.80, "ganhar massa");
        Paciente paciente2 = new Paciente("456", "teste2", LocalDate.of(2000, 8, 12),
                16999997777L, "teste2@email.com",80.0, 1.72, "emagrecer");

        cadastrarPacienteUseCase.cadastrar(paciente1);
        cadastrarPacienteUseCase.cadastrar(paciente2);

        // CDU0013 – Buscar Paciente
        buscarPacienteUseCase.findByCpf("123").ifPresent(System.out::println);
        buscarPacienteUseCase.findByCpf("456").ifPresent(System.out::println);
        System.out.println("--------------");

        // CDU002 – Editar paciente
        System.out.println("Editar Paciente");
        Paciente paciente1Update = new Paciente("123", "teste update", LocalDate.of(2000, 7, 2),
                16999990000L, "teste@email.com",90.0, 1.82, "ganhar massa");

        if (editarPacienteUseCase.editar(paciente1Update)) {
            System.out.println("Atualizado com sucesso");
            buscarPacienteUseCase.findByCpf("123").ifPresent(System.out::println);
        }
        else System.out.println("Erro ao atualizar");
        System.out.println("--------------");

        // [RF003]/[RF004] Inativar/Ativar Paciente
        System.out.println("Inativar Paciente");
        if (ativarPacienteUseCase.inativar(paciente2)) {
            System.out.println("Desativado com sucesso");
            buscarPacienteUseCase.findByCpf("456").ifPresent(System.out::println);
        }
        else System.out.println("Erro ao desativar");
        System.out.println("--------------");

        System.out.println("Ativar Paciente");
        if (ativarPacienteUseCase.ativar(paciente2)) {
            System.out.println("Ativado com sucesso");
            buscarPacienteUseCase.findByCpf("456").ifPresent(System.out::println);
        }
        else System.out.println("Erro ao ativar");
        System.out.println("--------------");
    }

    private static void inicializarTestesDeCriacao(AlimentoDAO alimentoDAO, RefeicaoDAO refeicaoDAO, DiaDAO diaDAO, DietaDAO dietaDAO){
        // CDU003 – Importar alimentos
        System.out.println("Importar Alimentos");
        importarAlimentoUseCase.importarAlimentosCSV("src/main/resources/com/doo/sistemanutruco/docs/alimentos.csv");

        List<Alimento> alimentos = alimentoDAO.findAll();
        for (Alimento alimento : alimentos)
            System.out.println(alimento);
        System.out.println("-------------");

        // CDU0012 – Cadastrar Refeição
        System.out.println("Cadastrar Refeição");
        List<Alimento> cafeDaManhaAlimentos = new ArrayList<>();
        cafeDaManhaAlimentos.add(alimentos.get(3)); // Pão
        cafeDaManhaAlimentos.add(alimentos.get(7)); // Queijo
        cafeDaManhaAlimentos.add(alimentos.get(8)); // Ovo
        Refeicao cafeDaManha = new Refeicao("Café da Manhã", "Primeira refeição do dia", "Energia para o dia", cafeDaManhaAlimentos);

        List<Alimento> almocoAlimentos = new ArrayList<>();
        almocoAlimentos.add(alimentos.get(0)); // Arroz
        almocoAlimentos.add(alimentos.get(1)); // Feijão
        almocoAlimentos.add(alimentos.get(6)); // Frango
        Refeicao almoco = new Refeicao("Almoço", "Refeição do meio-dia", "Para ganhar massa", almocoAlimentos);

        List<Alimento> jantaAlimentos = new ArrayList<>();
        jantaAlimentos.add(alimentos.get(0)); // Arroz
        jantaAlimentos.add(alimentos.get(1)); // Feijão
        jantaAlimentos.add(alimentos.get(5)); // Banana
        jantaAlimentos.add(alimentos.get(9)); // Tomate
        Refeicao jantar = new Refeicao("janta", "Refeição final do dia","Alimentação basica", jantaAlimentos);

        cadastrarRefeicaoUseCase.cadastrar(cafeDaManha);
        cadastrarRefeicaoUseCase.cadastrar(almoco);
        cadastrarRefeicaoUseCase.cadastrar(jantar);

        for (Refeicao refeicao : refeicaoDAO.findAll())
            System.out.println(refeicao);
        System.out.println("--------------");

        // CDU0011 – Cadastrar Dia
        System.out.println("Cadastrar Dia");
        List<Refeicao> refeicoesSegunda = new ArrayList<>();
        refeicoesSegunda.add(cafeDaManha);
        refeicoesSegunda.add(almoco);
        Dia segunda = new Dia(DayOfWeek.MONDAY, refeicoesSegunda);
        cadastrarDiaUseCase.cadastrar(segunda);
        System.out.println(diaDAO.findAll().get(0));
        System.out.println("--------------");

        // CDU004 – Cadastrar dieta
        System.out.println("Cadastrar Dieta");
        Dieta dieta1 = new Dieta("dieta1","ficar saudável", List.of(segunda));
        cadastrarDietaUseCase.cadastrar(dieta1);
        System.out.println(dietaDAO.findAll().get(0));
        System.out.println("--------------");
    }

    private static void inicializarTestesDieta(DietaDAO dietaDAO){
        // CDU00X - Buscar dieta
        System.out.println("Buscar Dieta");
        Dieta dieta1 = buscarDietaUseCase.findByNome("dieta1").get();
        System.out.println(dieta1);
        System.out.println("--------------");

        // CDU005 - Clonar dieta
        System.out.println("Clonar Dieta");
        Dieta dietaClonada = clonarDietaUseCase.clonarDieta(dieta1).get();
        System.out.println(dietaClonada);
        System.out.println("--------------");

        // CDU006 - Inativar dieta
        System.out.println("Inativar Dieta");
        ativarDietaUseCase.inativar(dieta1);
        System.out.println("Dieta: " + dieta1.getNome() + ", inativo: " + dieta1.getInativo());
        System.out.println("--------------");

        // CDU00XX - Ativar dieta
        System.out.println("Ativar Dieta");
        ativarDietaUseCase.ativar(dieta1);
        System.out.println("Dieta: " + dieta1.getNome() + ", inativo: " + dieta1.getInativo());
        System.out.println("--------------");
    }
}
