package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.repository.util.AbstractTemplateSqlDAO;
import com.doo.sistemanutruco.usecases.alimento.AlimentoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SqliteAlimentoDAO extends AbstractTemplateSqlDAO<Alimento, Integer> implements AlimentoDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Alimento (nome, calorias, carboidratos, proteinas, sodio, gluten, lactose, gorduras) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Alimento SET nome = ?, calorias = ?, carboidratos = ?, proteinas = ?, sodio = ?, gluten = ?, lactose = ?, gorduras = ? WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM Alimento WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM Alimento WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM Alimento";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM Alimento WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(Alimento entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setDouble(2, entity.getCalorias());
        stmt.setDouble(3, entity.getCarboidratos());
        stmt.setDouble(4, entity.getProteinas());
        stmt.setDouble(5, entity.getSodio());
        stmt.setBoolean(6, entity.getGluten());
        stmt.setBoolean(7, entity.getLactose());
        stmt.setDouble(8, entity.getGorduras());
        if (entity.getId() != null) {
            stmt.setInt(9, entity.getId());
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
    protected Alimento getEntityFromResultSet(ResultSet rs) throws SQLException {
        Alimento alimento = new Alimento();
        alimento.setId(rs.getInt("id"));
        alimento.setNome(rs.getString("nome"));
        alimento.setCalorias(rs.getDouble("calorias"));
        alimento.setCarboidratos(rs.getDouble("carboidratos"));
        alimento.setProteinas(rs.getDouble("proteinas"));
        alimento.setSodio(rs.getDouble("sodio"));
        alimento.setGluten(rs.getBoolean("gluten"));
        alimento.setLactose(rs.getBoolean("lactose"));
        alimento.setGorduras(rs.getDouble("gorduras"));
        return alimento;
    }

    @Override
    protected Integer getEntityKey(Alimento entity) {
        return entity.getId();
    }

    @Override
    public Optional<Alimento> findByNome(String nome) {
        return selectBy("nome", nome).stream().findFirst();
    }
}