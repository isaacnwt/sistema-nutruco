package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.util.AbstractTemplateSqlDAO;
import com.doo.sistemanutruco.repository.util.ConnectionFactory;
import com.doo.sistemanutruco.usecases.dieta.DietaDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SqliteDietaDAO extends AbstractTemplateSqlDAO<Dieta, Integer> implements DietaDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Dieta (nome, objetivo, dataInicio, dataFim, inativo, caloriasDaDieta, carboidratosDaDieta, proteinasDaDieta, sodioDaDieta, dietaContemGluten, dietaContemLactose, gordurasDaDieta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Dieta SET nome = ?, objetivo = ?, dataInicio = ?, dataFim = ?, inativo = ?, caloriasDaDieta = ?, carboidratosDaDieta = ?, proteinasDaDieta = ?, sodioDaDieta = ?, dietaContemGluten = ?, dietaContemLactose = ?, gordurasDaDieta = ? WHERE id = ?";
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
        stmt.setDate(3, Date.valueOf(entity.getDataInicio()));
        stmt.setDate(4, Date.valueOf(entity.getDataFim()));
        stmt.setBoolean(5, entity.isInativo());
        stmt.setDouble(6, entity.getCaloriasDaDieta());
        stmt.setDouble(7, entity.getCarboidratosDaDieta());
        stmt.setDouble(8, entity.getProteinasDaDieta());
        stmt.setDouble(9, entity.getSodioDaDieta());
        stmt.setBoolean(10, entity.isDietaContemGluten());
        stmt.setBoolean(11, entity.isDietaContemLactose());
        stmt.setDouble(12, entity.getGordurasDaDieta());
        if (entity.getId() != null) {
            stmt.setInt(13, entity.getId());
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
    protected Dieta getEntityFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        String objetivo = rs.getString("objetivo");
        LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
        LocalDate dataFim = rs.getDate("dataFim").toLocalDate();
        boolean inativo = rs.getBoolean("inativo");
        double caloriasDaDieta = rs.getDouble("caloriasDaDieta");
        double carboidratosDaDieta = rs.getDouble("carboidratosDaDieta");
        double proteinasDaDieta = rs.getDouble("proteinasDaDieta");
        double sodioDaDieta = rs.getDouble("sodioDaDieta");
        boolean dietaContemGluten = rs.getBoolean("dietaContemGluten");
        boolean dietaContemLactose = rs.getBoolean("dietaContemLactose");
        double gordurasDaDieta = rs.getDouble("gordurasDaDieta");
        List<Dia> dias = new SqliteDiaDAO().findByDietaId(id);

        Dieta dieta = new Dieta(nome, objetivo, dias, dataInicio, dataFim);
        dieta.setId(id);
        dieta.setInativo(inativo);
        dieta.setCaloriasDaDieta(caloriasDaDieta);
        dieta.setCarboidratosDaDieta(carboidratosDaDieta);
        dieta.setProteinasDaDieta(proteinasDaDieta);
        dieta.setSodioDaDieta(sodioDaDieta);
        dieta.setDietaContemGluten(dietaContemGluten);
        dieta.setDietaContemLactose(dietaContemLactose);
        dieta.setGordurasDaDieta(gordurasDaDieta);

        return dieta;
    }

    @Override
    protected Integer getEntityKey(Dieta entity) {
        return entity.getId();
    }

    @Override
    public List<Dieta> findByPaciente(Paciente paciente) {
        String sql = createSelectBySql("pacienteCpf");
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setFilterToPreparedStatement(paciente.getCpf(), stmt);
            ResultSet rs = stmt.executeQuery();
            List<Dieta> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(getEntityFromResultSet(rs));
            }
            return resultList;
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
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
