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

    public Optional<Paciente> findByNome(String nome){
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome do paciente é obrigatório");
        return pacienteDAO.findByNome(nome);
    }

    public Optional<Paciente> findByCpf(String cpf){
        if (cpf == null || cpf.isEmpty())
            throw new IllegalArgumentException("Cpf do paciente é obrigatório");
        return pacienteDAO.findByCpf(cpf);
    }
}
