package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.util.AbstractTemplateSqlDAO;
import com.doo.sistemanutruco.repository.util.ConnectionFactory;
import com.doo.sistemanutruco.usecases.dia.DiaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

public class SqliteDiaDAO extends AbstractTemplateSqlDAO<Dia, Integer> implements DiaDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Dia (diaDaSemana, inativo, calorias, carboidratos, proteinas, sodio, gorduras) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Dia SET diaDaSemana = ?, inativo = ?, calorias = ?, carboidratos = ?, proteinas = ?, sodio = ?, gorduras = ? WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM Dia WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM Dia WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM Dia";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM Dia WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(Dia entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getDiaDaSemana().name());
        stmt.setBoolean(2, entity.isInativo());
        stmt.setDouble(3, entity.getCaloriasNoDia());
        stmt.setDouble(4, entity.getCarboidratosNoDia());
        stmt.setDouble(5, entity.getProteinasNoDia());
        stmt.setDouble(6, entity.getSodioNoDia());
        stmt.setDouble(7, entity.getGordurasNoDia());
        if (entity.getId() != null) {
            stmt.setInt(8, entity.getId());
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
    protected Dia getEntityFromResultSet(ResultSet rs) throws SQLException {
        Dia dia = new Dia();
        dia.setId(rs.getInt("id"));
        dia.setDiaDaSemana(DayOfWeek.valueOf(rs.getString("diaDaSemana")));
        dia.setInativo(rs.getBoolean("inativo"));
        dia.setCaloriasNoDia(rs.getDouble("calorias"));
        dia.setCarboidratosNoDia(rs.getDouble("carboidratos"));
        dia.setProteinasNoDia(rs.getDouble("proteinas"));
        dia.setSodioNoDia(rs.getDouble("sodio"));
        dia.setGordurasNoDia(rs.getDouble("gorduras"));
        return dia;
    }

    @Override
    protected Integer getEntityKey(Dia entity) {
        return entity.getId();
    }

    @Override
    public Integer create(Dia dia) {
        super.create(dia);
        Integer dietaId = getLastInsertId();
        dia.setId(dietaId);
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
    public void atribuirRefeicaoADia(Dia dia, Refeicao refeicao) {
        String sql = "INSERT INTO DiaRefeicao (diaId, refeicaoId) VALUES (?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, dia.getId());
            stmt.setInt(2, refeicao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atribuir a refeição ao dia: " + e.getMessage(), e);
        }
    }


}
