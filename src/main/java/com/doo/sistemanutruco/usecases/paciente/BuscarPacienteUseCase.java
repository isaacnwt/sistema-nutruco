package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;

import java.util.List;
import java.util.Optional;

public class BuscarPacienteUseCase {
    private final PacienteDAO pacienteDAO;

    public BuscarPacienteUseCase(PacienteDAO pacienteDAO){
        this.pacienteDAO = pacienteDAO;
    }

    public List<Paciente> findAll(){
        return pacienteDAO.findAll();
    }

    public List<Paciente> findByNome(String nome){
        // Adicionar validações

        return pacienteDAO.findByNome(nome);
    }

    public Optional<Paciente> findByCpf(String cpf){
        // Adicionar validações

        return pacienteDAO.findByCpf(cpf);
    }
}
