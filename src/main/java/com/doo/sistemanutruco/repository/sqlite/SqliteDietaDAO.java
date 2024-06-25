package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.util.AbstractTemplateSqlDAO;
import com.doo.sistemanutruco.repository.util.ConnectionFactory;
import com.doo.sistemanutruco.usecases.dieta.DietaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteDietaDAO extends AbstractTemplateSqlDAO<Dieta, Integer> implements DietaDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Dieta (nome, objetivo, inativo, calorias, carboidratos, proteinas, sodio, contemGluten, contemLactose, gorduras) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Dieta SET nome = ?, objetivo = ?, inativo = ?, calorias = ?, carboidratos = ?, proteinas = ?, sodio = ?, contemGluten = ?, contemLactose = ?, gorduras = ? WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM Dieta WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM Dieta WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM Dieta";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM Dieta WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(Dieta entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getObjetivo());
        stmt.setBoolean(3, entity.isInativo());
        stmt.setDouble(4, entity.getCaloriasDaDieta());
        stmt.setDouble(5, entity.getCarboidratosDaDieta());
        stmt.setDouble(6, entity.getProteinasDaDieta());
        stmt.setDouble(7, entity.getSodioDaDieta());
        stmt.setBoolean(8, entity.isDietaContemGluten());
        stmt.setBoolean(9, entity.isDietaContemLactose());
        stmt.setDouble(10, entity.getGordurasDaDieta());
        if (entity.getId() != null) {
            stmt.setInt(11, entity.getId());
        }
    }

    @Override
    protected void setKeyToPreparedStatement(Integer key, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, key);
    }

    @Override
    protected void setFilterToPreparedStatement(Object filter, PreparedStatement stmt) throws SQLException {
        if (filter instanceof String) {
            stmt.setString(1, (String) filter);
        } else if (filter instanceof Integer) {
            stmt.setInt(1, (Integer) filter);
        }
    }

    @Override
    protected Dieta getEntityFromResultSet(ResultSet rs) throws SQLException {
        Dieta dieta = new Dieta();
        dieta.setId(rs.getInt("id"));
        dieta.setNome(rs.getString("nome"));
        dieta.setObjetivo(rs.getString("objetivo"));
        dieta.setInativo(rs.getBoolean("inativo"));
        dieta.setCaloriasDaDieta(rs.getDouble("calorias"));
        dieta.setCarboidratosDaDieta(rs.getDouble("carboidratos"));
        dieta.setProteinasDaDieta(rs.getDouble("proteinas"));
        dieta.setSodioDaDieta(rs.getDouble("sodio"));
        dieta.setDietaContemGluten(rs.getBoolean("contemGluten"));
        dieta.setDietaContemLactose(rs.getBoolean("contemLactose"));
        dieta.setGordurasDaDieta(rs.getDouble("gorduras"));
        return dieta;
    }

    @Override
    protected Integer getEntityKey(Dieta entity) {
        return entity.getId();
    }

    @Override
    public Integer create(Dieta dieta) {
        super.create(dieta);
        Integer dietaId = getLastInsertId();
        dieta.setId(dietaId);
        return dietaId;
    }

    private Integer getLastInsertId() {
        String sql = "SELECT last_insert_rowid()";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dieta> findByPaciente(Paciente paciente) {
        String sql = "SELECT d.* FROM Dieta d JOIN PacienteDieta pd ON d.id = pd.dietaId WHERE pd.pacienteCpf = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, paciente.getCpf());
            ResultSet rs = stmt.executeQuery();
            List<Dieta> dietas = new ArrayList<>();
            while (rs.next()) {
                dietas.add(getEntityFromResultSet(rs));
            }
            return dietas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void atribuirDiaADieta(Dieta dieta, Dia dia) {
        String sql = "INSERT INTO DietaDia (dietaId, diaId) VALUES (?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, dieta.getId());
            stmt.setInt(2, dia.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
