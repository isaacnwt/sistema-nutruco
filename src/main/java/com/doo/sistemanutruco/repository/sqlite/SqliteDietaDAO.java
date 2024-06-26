package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
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

import static java.sql.Date.valueOf;

public class SqliteDietaDAO extends AbstractTemplateSqlDAO<Dieta, Integer> implements DietaDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Dieta (nome, objetivo, ativa, calorias, carboidratos, proteinas, sodio, contemGluten, contemLactose, gorduras, dataInicio, dataFim) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Dieta SET nome = ?, objetivo = ?, ativa = ?, calorias = ?, carboidratos = ?, proteinas = ?, sodio = ?, contemGluten = ?, contemLactose = ?, gorduras = ?, dataInicio = ?, dataFim = ? WHERE id = ?";
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
        stmt.setBoolean(3, entity.isAtiva());
        stmt.setDouble(4, entity.getCaloriasDaDieta());
        stmt.setDouble(5, entity.getCarboidratosDaDieta());
        stmt.setDouble(6, entity.getProteinasDaDieta());
        stmt.setDouble(7, entity.getSodioDaDieta());
        stmt.setBoolean(8, entity.isDietaContemGluten());
        stmt.setBoolean(9, entity.isDietaContemLactose());
        stmt.setDouble(10, entity.getGordurasDaDieta());
        stmt.setDate(11, valueOf(entity.getDataInicio()));
        stmt.setDate(12, valueOf(entity.getDataFim()));

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
        dieta.setAtiva(rs.getBoolean("ativa"));
        dieta.setCaloriasDaDieta(rs.getDouble("calorias"));
        dieta.setCarboidratosDaDieta(rs.getDouble("carboidratos"));
        dieta.setProteinasDaDieta(rs.getDouble("proteinas"));
        dieta.setSodioDaDieta(rs.getDouble("sodio"));
        dieta.setDietaContemGluten(rs.getBoolean("contemGluten"));
        dieta.setDietaContemLactose(rs.getBoolean("contemLactose"));
        dieta.setGordurasDaDieta(rs.getDouble("gorduras"));
        dieta.setDataInicio(rs.getDate("dataInicio").toLocalDate());
        dieta.setDataFim(rs.getDate("dataFim").toLocalDate());
        return dieta;
    }


    @Override
    protected Integer getEntityKey(Dieta entity) {
        return entity.getId();
    }

    @Override
    public Integer create(Dieta dieta) {
        String sql = createSaveSql();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setEntityToPreparedStatement(dieta, stmt);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
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
    public Optional<Dieta> clone(Dieta dieta) {
        Dieta dietaClonada = new Dieta();
        dietaClonada.setNome(dieta.getNome() + " clone");
        dietaClonada.setObjetivo(dieta.getObjetivo());
        dietaClonada.setAtiva(dieta.isAtiva());
        dietaClonada.setCaloriasDaDieta(dieta.getCaloriasDaDieta());
        dietaClonada.setCarboidratosDaDieta(dieta.getCarboidratosDaDieta());
        dietaClonada.setProteinasDaDieta(dieta.getProteinasDaDieta());
        dietaClonada.setSodioDaDieta(dieta.getSodioDaDieta());
        dietaClonada.setDietaContemGluten(dieta.isDietaContemGluten());
        dietaClonada.setDietaContemLactose(dieta.isDietaContemLactose());
        dietaClonada.setGordurasDaDieta(dieta.getGordurasDaDieta());

        LocalDate dataInicioOriginal = dieta.getDataInicio();
        LocalDate dataFimOriginal = dieta.getDataFim();

        if (dataInicioOriginal != null && dataFimOriginal != null) {
            long duracao = dataFimOriginal.toEpochDay() - dataInicioOriginal.toEpochDay();
            LocalDate novaDataInicio = dataFimOriginal.plusDays(1);
            LocalDate novaDataFim = novaDataInicio.plusDays(duracao);

            dietaClonada.setDataInicio(novaDataInicio);
            dietaClonada.setDataFim(novaDataFim);
        } else {
            dietaClonada.setDataInicio(null);
            dietaClonada.setDataFim(null);
        }

        return Optional.of(dietaClonada);
    }

    @Override
    public void atribuirDiaADieta(Integer dietaId, Dia dia) {
        String sql = "INSERT INTO DietaDia (dietaId, diaId) VALUES (?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, dietaId);
            stmt.setInt(2, dia.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerDietaDoPaciente(String pacienteId, Integer dietaId) {
        String sql = "DELETE FROM PacienteDieta WHERE pacienteCpf = ? AND dietaId = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(pacienteId));
            stmt.setInt(2, dietaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
