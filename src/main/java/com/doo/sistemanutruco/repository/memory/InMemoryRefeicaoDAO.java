package com.doo.sistemanutruco.repository.memory;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.usecases.refeicao.RefeicaoDAO;

import java.util.*;

public class InMemoryRefeicaoDAO implements RefeicaoDAO {
    public static final Map<Integer, Refeicao> db = new LinkedHashMap<>();
    private static Integer id = 0;

    @Override
    public Integer create(Refeicao refeicao) {
        refeicao.setId(++id);
        db.put(id, refeicao);
        return id;
    }

    @Override
    public Optional<Refeicao> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Refeicao> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Refeicao refeicao) {
        Integer id = refeicao.getId();
        if(db.containsKey(id)){
            db.replace(id, refeicao);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Refeicao refeicao) {
        return deleteByKey(refeicao.getId());
    }

    @Override
    public Optional<Refeicao> findByNome(String nome) {
        return db.values().stream()
                .filter(refeicao -> refeicao.getNome().equals(nome))
                .findAny();
    }
}
