package com.doo.sistemanutruco.repository.memory;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.dieta.DietaDAO;

import java.util.*;

public class InMemoryDietaDAO implements DietaDAO {
    public static final Map<Integer, Dieta> db = new LinkedHashMap<>();
    public static int id = 0;

    @Override
    public Integer create(Dieta dieta){
        dieta.setId(++id);
        db.put(id, dieta);
        return id;
    }

    @Override
    public Optional<Dieta> findOne(Integer key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<Dieta> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Dieta dieta) {
        int dietaId = dieta.getId();
        if (db.containsKey(dietaId)){
            db.replace(dietaId, dieta);
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
    public boolean delete(Dieta type) {
        return false;
    }

    @Override
    public List<Dieta> selectBy(String field, Object value) {
        return List.of();
    }

    @Override
    public List<Dieta> findByPaciente(Paciente paciente) {
        return List.of();
    }

    @Override
    public void atribuirDiaADieta(Dieta dieta, Dia dia) {

    }
}
