package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.util.AbstractTemplateSqlDAO;
import com.doo.sistemanutruco.repository.util.ConnectionFactory;
import com.doo.sistemanutruco.usecases.dia.DiaDAO;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteDiaDAO extends AbstractTemplateSqlDAO<Dia, Integer> implements DiaDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Dia (diaDaSemana, inativo, caloriasNoDia, carboidratosNoDia, proteinasNoDia, sodioNoDia, gordurasNoDia) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Dia SET diaDaSemana = ?, inativo = ?, caloriasNoDia = ?, carboidratosNoDia = ?, proteinasNoDia = ?, sodioNoDia = ?, gordurasNoDia = ? WHERE id = ?";
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
        stmt.setString(1, entity.getDiaDaSemana().toString());
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
        stmt.setString(1, filter.toString());
    }

    @Override
    protected Dia getEntityFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        DayOfWeek diaDaSemana = DayOfWeek.valueOf(rs.getString("diaDaSemana"));
        double caloriasNoDia = rs.getDouble("caloriasNoDia");
        double carboidratosNoDia = rs.getDouble("carboidratosNoDia");
        double proteinasNoDia = rs.getDouble("proteinasNoDia");
        double sodioNoDia = rs.getDouble("sodioNoDia");
        double gordurasNoDia = rs.getDouble("gordurasNoDia");
        List<Refeicao> refeicoes = new SqliteRefeicaoDAO().findByDiaId(id);

        Dia dia = new Dia(diaDaSemana, refeicoes);
        dia.setId(id);
        dia.setCaloriasNoDia(caloriasNoDia);
        dia.setCarboidratosNoDia(carboidratosNoDia);
        dia.setProteinasNoDia(proteinasNoDia);
        dia.setSodioNoDia(sodioNoDia);
        dia.setGordurasNoDia(gordurasNoDia);

        return dia;
    }

    @Override
    protected Integer getEntityKey(Dia entity) {
        return entity.getId();
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
        }
    }

    @Override
    public List<Dia> findByDietaId(Integer dietaId) {
        String sql = createSelectBySql("dietaId");
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setFilterToPreparedStatement(dietaId, stmt);
            ResultSet rs = stmt.executeQuery();
            List<Dia> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(getEntityFromResultSet(rs));
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
