package com.doo.sistemanutruco.repository.memory;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.usecases.alimento.AlimentoDAO;

import java.util.*;

public class InMemoryAlimentoDAO implements AlimentoDAO {
    public static final Map<Integer, Alimento> db = new LinkedHashMap<>();
    public static int id = 0;

    @Override
    public Integer create(Alimento alimento) {
        alimento.setId(++id);
        db.put(id, alimento);
        return id;
    }

    @Override
    public Optional<Alimento> findOne(Integer key) {
        if (db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Alimento> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Alimento alimento) {
        Integer id = alimento.getId();
        if (db.containsKey(id)) {
            db.replace(id, alimento);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Alimento> findByNome(String nome) {
        return db.values().stream()
                .filter(alimento -> alimento.getNome().equals(nome))
                .findAny();
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if (db.containsKey(key)) {
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Alimento alimento) {
        return deleteByKey(alimento.getId());
    }

    @Override
    public List<Alimento> selectBy(String field, Object value) {
        return List.of();
    }
}


