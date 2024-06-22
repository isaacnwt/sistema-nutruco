package com.doo.sistemanutruco.repository.memory;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.paciente.PacienteDAO;

import java.util.*;

public class InMemoryPacienteDAO implements PacienteDAO {

    private static final Map<String, Paciente> db = new LinkedHashMap<>();

    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        return db.values().stream()
                .filter(user -> user.getCpf().equals(cpf))
                .findAny();
    }

    @Override
    public Optional<Paciente> findByNome(String nome) {
        return db.values().stream()
                .filter(user -> user.getNome().equals(nome))
                .findAny();
    }

    @Override
    public String create(Paciente paciente) {
        db.put(paciente.getCpf(), paciente);
        return paciente.getCpf();
    }

    @Override
    public Optional<Paciente> findOne(String cpf) {
        return db.values().stream()
                .filter(user -> user.getCpf().equals(cpf))
                .findAny();
    }

    @Override
    public List<Paciente> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Paciente paciente) {
        String cpf = paciente.getCpf();
        if (db.containsKey(cpf)) {
            db.replace(cpf, paciente);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String cpf) {
        if (db.containsKey(cpf)) {
            db.remove(cpf);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Paciente paciente) {
        return deleteByKey(paciente.getCpf());
    }

    @Override
    public List<Paciente> selectBy(String field, Object value) {
        return List.of();
    }


}
