package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.utils.NullEntityException;
import com.doo.sistemanutruco.usecases.utils.Validator;

public class PacienteValidator implements Validator<Paciente> {
    public void validar(Paciente paciente) {
        if (paciente == null)
            throw new NullEntityException("Paciente não pode ser null");
        if (paciente.getCpf() == null || paciente.getCpf().isEmpty())
            throw new IllegalArgumentException("Cpf do paciente é obrigatório");
        if (paciente.getNome() == null || paciente.getNome().isEmpty())
            throw new IllegalArgumentException("Nome do paciente é obrigatório");
        if (paciente.getDataNascimento() == null)
            throw new IllegalArgumentException("Data de nascimento do paciente é obrigatório");
        if (paciente.getTelefone() == null)
            throw new IllegalArgumentException("Telefone do paciente é obrigatório");
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty())
            throw new IllegalArgumentException("Email do paciente é obrigatório");
        if (paciente.getPeso() == null)
            throw new IllegalArgumentException("Peso do paciente é obrigatório");
        if (paciente.getAltura() == null)
            throw new IllegalArgumentException("Altura do paciente é obrigatório");
        if (paciente.getObjetivo() == null || paciente.getObjetivo().isEmpty())
            throw new IllegalArgumentException("Objetivo do paciente é obrigatório");
    }
}
