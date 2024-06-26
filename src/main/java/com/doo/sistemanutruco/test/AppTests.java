package com.doo.sistemanutruco.test;


import com.doo.sistemanutruco.usecases.alimento.ImportarAlimentosUseCase;
import com.doo.sistemanutruco.usecases.dia.CadastrarDiaUseCase;
import com.doo.sistemanutruco.usecases.dia.EditarDiaUseCase;
import com.doo.sistemanutruco.usecases.dia.ExcluirDiaUseCase;
import com.doo.sistemanutruco.usecases.dieta.*;
import com.doo.sistemanutruco.usecases.paciente.*;
import com.doo.sistemanutruco.usecases.refeicao.CadastrarRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.EditarRefeicaoUseCase;
import com.doo.sistemanutruco.usecases.refeicao.ExcluirRefeicaoUseCase;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppTests {

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
    private static ClonarDietaUseCase clonarDietaUseCase;
    private static AtribuirDietaUseCase atribuirDietaUseCase;

    public static void main(String[] args) {
//        inicializacao();
//
//        inicializarTestesPaciente();
//
//        inicializarTestesAlimento();
//
//        inicializarTestesRefeicao();
//
//        inicializarTestesDia();
//
//        inicializarTestesDieta();
//    }
//
//    private static void inicializacao() {
//        inicializarInMemory();
//        inicializarUseCases();
//    }
//
//    private static void inicializarInMemory(){
//        inMemoryPacienteDAO = new InMemoryPacienteDAO();
//        inMemoryAlimentoDAO = new InMemoryAlimentoDAO();
//        inMemoryRefeicaoDAO = new InMemoryRefeicaoDAO();
//        inMemoryDiaDAO = new InMemoryDiaDAO();
//        inMemoryDietaDAO = new InMemoryDietaDAO();
//    }
//
//    private static void inicializarUseCases() {
//        cadastrarPacienteUseCase = new CadastrarPacienteUseCase(inMemoryPacienteDAO);
//        editarPacienteUseCase = new EditarPacienteUseCase(inMemoryPacienteDAO);
//        buscarPacienteUseCase = new BuscarPacienteUseCase(inMemoryPacienteDAO);
//        ativarPacienteUseCase = new AtivarPacienteUseCase(inMemoryPacienteDAO);
//        importarAlimentoUseCase = new ImportarAlimentosUseCase(inMemoryAlimentoDAO);
//        cadastrarRefeicaoUseCase = new CadastrarRefeicaoUseCase(inMemoryRefeicaoDAO);
//        editarRefeicaoUseCase = new EditarRefeicaoUseCase(inMemoryRefeicaoDAO);
//        excluirRefeicaoUseCase = new ExcluirRefeicaoUseCase(inMemoryRefeicaoDAO);
//        cadastrarDiaUseCase = new CadastrarDiaUseCase(inMemoryDiaDAO);
//        editarDiaUseCase = new EditarDiaUseCase(inMemoryDiaDAO);
//        excluirDiaUseCase = new ExcluirDiaUseCase(inMemoryDiaDAO);
//        ativarDietaUseCase = new AtivarDietaUseCase(inMemoryDietaDAO);
//        buscarDietaUseCase = new BuscarDietaUseCase(inMemoryDietaDAO);
//        cadastrarDietaUseCase = new CadastrarDietaUseCase(inMemoryDietaDAO);
//        clonarDietaUseCase = new ClonarDietaUseCase(inMemoryDietaDAO);
//        atribuirDietaUseCase = new AtribuirDietaUseCase(inMemoryPacienteDAO);
//    }
//
//    private static void inicializarTestesPaciente(){
//        System.out.println("Paciente Use Cases:\n");
//        // CDU001 – Cadastrar paciente
//        System.out.println("Cadastrar Paciente/Buscar Paciente");
//        Paciente paciente1 = new Paciente("123", "teste", LocalDate.of(2000, 7, 2),
//                16999998888L, "teste@email.com",90.0, 1.80, "ganhar massa");
//        Paciente paciente2 = new Paciente("456", "teste2", LocalDate.of(2000, 8, 12),
//                16999997777L, "teste2@email.com",80.0, 1.72, "emagrecer");
//
//        cadastrarPacienteUseCase.cadastrar(paciente1);
//        cadastrarPacienteUseCase.cadastrar(paciente2);
//
//        // CDU0013 – Buscar Paciente
//        buscarPacienteUseCase.findByCpf("123").ifPresent(System.out::println);
//        buscarPacienteUseCase.findByNome("teste2").ifPresent(System.out::println);
//        System.out.println("--------------");
//
//        // CDU002 - Editar Paciente
//        System.out.println("Editar Paciente");
//        paciente1.setNome("Teste 1 - Editado");
//        paciente1.setAltura(1.68);
//
//        if (editarPacienteUseCase.editar(paciente1)) {
//            System.out.println("Atualizado com sucesso");
//            buscarPacienteUseCase.findByCpf("123").ifPresent(System.out::println);
//        }
//        else System.out.println("Erro ao atualizar");
//        System.out.println("--------------");
//
//        // [RF003]/[RF004] Inativar/Ativar Paciente
//        System.out.println("Inativar Paciente");
//        if (ativarPacienteUseCase.inativar(paciente2)) {
//            System.out.println("Desativado com sucesso");
//            buscarPacienteUseCase.findByCpf("456").ifPresent(paciente ->
//                    System.out.println("Paciente: "+paciente.getNome()+", Inativo: "+paciente.getInativo()));
//        }
//        else System.out.println("Erro ao desativar");
//        System.out.println("--------------");
//
//        System.out.println("Ativar Paciente");
//        if (ativarPacienteUseCase.ativar(paciente2)) {
//            System.out.println("Ativado com sucesso");
//            buscarPacienteUseCase.findByCpf("456").ifPresent(paciente ->
//                    System.out.println("Paciente: "+paciente.getNome()+", Inativo: "+paciente.getInativo()));
//        }
//        else System.out.println("Erro ao ativar");
//        System.out.println("--------------");
//    }
//
//    private static void inicializarTestesAlimento(){
//        System.out.println("Alimento Use Cases:\n");
//        // CDU003 – Importar alimentos
//        System.out.println("Importar Alimentos");
//        importarAlimentoUseCase.importarAlimentosCSV("src/main/resources/com/doo/sistemanutruco/docs/alimentos.csv");
//
//        List<Alimento> alimentos = inMemoryAlimentoDAO.findAll();
//        for (Alimento alimento : alimentos)
//            System.out.println(alimento);
//        System.out.println("-------------");
//    }
//
//    private static void inicializarTestesRefeicao(){
//        System.out.println("Refeicao Use Cases:\n");
//        List<Alimento> alimentos = inMemoryAlimentoDAO.findAll();
//        // CDU0012 – Cadastrar Refeição
//        System.out.println("Cadastrar Refeição");
//        List<Alimento> cafeDaManhaAlimentos = new ArrayList<>();
//        cafeDaManhaAlimentos.add(alimentos.get(3)); // Pão
//        cafeDaManhaAlimentos.add(alimentos.get(7)); // Queijo
//        cafeDaManhaAlimentos.add(alimentos.get(8)); // Ovo
//        Refeicao cafeDaManha = new Refeicao("Café da Manhã", "Primeira refeição do dia", "Energia para o dia", cafeDaManhaAlimentos);
//
//        List<Alimento> almocoAlimentos = new ArrayList<>();
//        almocoAlimentos.add(alimentos.get(0)); // Arroz
//        almocoAlimentos.add(alimentos.get(1)); // Feijão
//        almocoAlimentos.add(alimentos.get(6)); // Frango
//        Refeicao almoco = new Refeicao("Almoço", "Refeição do meio-dia", "Para ganhar massa", almocoAlimentos);
//
//        List<Alimento> jantaAlimentos = new ArrayList<>();
//        jantaAlimentos.add(alimentos.get(0)); // Arroz
//        jantaAlimentos.add(alimentos.get(1)); // Feijão
//        jantaAlimentos.add(alimentos.get(5)); // Banana
//        jantaAlimentos.add(alimentos.get(9)); // Tomate
//        Refeicao jantar = new Refeicao("janta", "Refeição final do dia","Alimentação basica", jantaAlimentos);
//
//        cadastrarRefeicaoUseCase.cadastrar(cafeDaManha);
//        cadastrarRefeicaoUseCase.cadastrar(almoco);
//        cadastrarRefeicaoUseCase.cadastrar(jantar);
//
//        for (Refeicao refeicao : inMemoryRefeicaoDAO.findAll())
//            System.out.println(refeicao);
//        System.out.println("--------------");
//
//        // [RF013] Editar Refeição
//        System.out.println("Editar Refeição");
//        Refeicao refeicao = inMemoryRefeicaoDAO.findAll().getFirst();
//        List<Alimento> alimentosDaRefeicao = refeicao.getAlimentos();
//        alimentosDaRefeicao.add(inMemoryAlimentoDAO.findAll().get(6));
//
//        refeicao.setAlimentos(alimentosDaRefeicao);
//        refeicao.setNome("Jantar");
//        refeicao.setObjetivo("Alimentação nutritiva");
//
//        if (editarRefeicaoUseCase.editar(refeicao)) {
//            System.out.println("Atualizado com sucesso");
//            inMemoryRefeicaoDAO.findByNome("Jantar").ifPresent(System.out::println);
//        }
//        else System.out.println("Erro ao atualizar");
//        System.out.println("--------------");
//
//        // TODO - [RF014] Excluir Refeição
//    }
//
//    private static void inicializarTestesDia(){
//        System.out.println("Dia Use Cases:\n");
//        // CDU0011 – Cadastrar Dia
//        System.out.println("Cadastrar Dia");
//        Dia segunda = new Dia(DayOfWeek.MONDAY, inMemoryRefeicaoDAO.findAll());
//        cadastrarDiaUseCase.cadastrar(segunda);
//        Dia terca = new Dia(DayOfWeek.TUESDAY, inMemoryRefeicaoDAO.findAll());
//        cadastrarDiaUseCase.cadastrar(terca);
//        for (Dia diaInMemory : inMemoryDiaDAO.findAll())
//            System.out.println(diaInMemory);
//        System.out.println("--------------");
//
//        // CDU0011 – Editar Dia
//        System.out.println("Editar Dia");
//        Dia dia = inMemoryDiaDAO.findAll().get(0);
//        List<Refeicao> novaListaRefeicao = new ArrayList<>();
//        novaListaRefeicao.add(inMemoryRefeicaoDAO.findAll().get(1));
//        dia.setRefeicoes(novaListaRefeicao);
//        editarDiaUseCase.editar(dia);
//        System.out.println(dia);
//        System.out.println("--------------");
//
//        // CDU0013 - Excluir dia
//        System.out.println("Excluir Dia");
//        excluirDiaUseCase.excluir(dia);
//        for (Dia diaInMemory : inMemoryDiaDAO.findAll())
//            System.out.println(diaInMemory);
//        System.out.println("--------------");
//
//    }
//
//    private static void inicializarTestesDieta() {
//        System.out.println("Dieta Use Cases:\n");
//        Dia segunda = inMemoryDiaDAO.findAll().get(0);
//
//        // CDU004 – Cadastrar dieta
//        System.out.println("Cadastrar Dieta");
//        Dieta dieta1 = new Dieta("dieta1","ficar saudável", List.of(segunda));
//        cadastrarDietaUseCase.atribuirDieta(dieta1);
//        System.out.println(inMemoryDietaDAO.findAll().get(0));
//        System.out.println("--------------");
//
//        // CDU00X - Buscar dieta
//        System.out.println("Buscar Dieta");
//        Dieta dietaEncontrada = buscarDietaUseCase.findByNome("dieta1").get();
//        System.out.println(dietaEncontrada);
//        System.out.println("--------------");
//
//        // CDU005 - Clonar dieta
//        System.out.println("Clonar Dieta");
//        Dieta dietaClonada = clonarDietaUseCase.clonarDieta(dietaEncontrada).get();
//        System.out.println(dietaClonada);
//        System.out.println("--------------");
//
//        // CDU006 - Inativar dieta
//        System.out.println("Inativar Dieta");
//        ativarDietaUseCase.inativar(dietaEncontrada);
//        System.out.println("Dieta: " + dietaEncontrada.getNome() + ", inativo: " + dietaEncontrada.isInativo());
//        System.out.println("--------------");
//
//        // CDU00XX - Ativar dieta
//        System.out.println("Ativar Dieta");
//        ativarDietaUseCase.ativar(dietaEncontrada);
//        System.out.println("Dieta: " + dietaEncontrada.getNome() + ", inativo: " + dietaEncontrada.isInativo());
//        System.out.println("--------------");
//
//        // CDU0018 - Atribuir dieta
//        System.out.println("Atribuir dieta");
//        Paciente paciente = inMemoryPacienteDAO.findByCpf("123").get();
//        atribuirDietaUseCase.atribuirDieta(paciente, dietaEncontrada);
//        System.out.println(paciente);
//        System.out.println("--------------");
//
//        // CDU0019 - Desatribuir dieta
//        System.out.println("Desatribuir dieta");
//        atribuirDietaUseCase.desatribuirDieta(paciente, dietaEncontrada);
//        System.out.println(paciente);
//        System.out.println("--------------");
    }
}
