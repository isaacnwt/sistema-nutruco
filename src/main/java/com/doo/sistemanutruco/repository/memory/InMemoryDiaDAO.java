package com.doo.sistemanutruco.repository.memory;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.usecases.dia.DiaDAO;

import java.util.*;

public class InMemoryDiaDAO implements DiaDAO {
    public static final Map<Integer, Dia> db = new LinkedHashMap<>();
    public static int id = 0;

    @Override
    public Integer create(Dia dia) {
        dia.setId(++id);
        db.put(id, dia);
        return id;
    }

    @Override
    public Optional<Dia> findOne(Integer key) {
        if (db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Dia> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Dia dia) {
        Integer id = dia.getId();
        if (db.containsKey(id)) {
            db.replace(id, dia);
            return true;
        }
        return false;
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
    public boolean delete(Dia dia) {
        return deleteByKey(dia.getId());
    }

    @Override
    public List<Dia> selectBy(String field, Object value) {
        return List.of();
    }
}
