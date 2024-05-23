package com.doo.sistemanutruco.main;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.memory.InMemoryPacienteDAO;
import com.doo.sistemanutruco.usecases.paciente.CadastrarPacienteUseCase;
import com.doo.sistemanutruco.usecases.paciente.PacienteDAO;

import java.time.LocalDate;
import java.util.Optional;

public class Main {

    private static CadastrarPacienteUseCase cadastrarPacienteUseCase;

    public static void main(String[] args) {
        PacienteDAO pacienteDAO = new InMemoryPacienteDAO();
        cadastrarPacienteUseCase = new CadastrarPacienteUseCase(pacienteDAO);

        Paciente paciente1 = new Paciente("123", "teste", LocalDate.of(2000, 7, 2), 16999998888L,
                "teste@email.com",90.0, 1.80, "ganhar massa");

        cadastrarPacienteUseCase.cadastrar(paciente1);

        Optional<Paciente> pacienteOptional = pacienteDAO.findByCpf("123");
        pacienteOptional.ifPresent(System.out::println);
    }
}
