package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.util.AbstractTemplateSqlDAO;
import com.doo.sistemanutruco.repository.util.ConnectionFactory;
import com.doo.sistemanutruco.usecases.refeicao.RefeicaoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteRefeicaoDAO extends AbstractTemplateSqlDAO<Refeicao, Integer> implements RefeicaoDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Refeicao (nome, descricao, objetivo, contemGluten, contemLactose, calorias, carboidratos, proteinas, sodio, gorduras) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Refeicao SET nome = ?, descricao = ?, objetivo = ?, contemGluten = ?, contemLactose = ?, calorias = ?, carboidratos = ?, proteinas = ?, sodio = ?, gorduras = ? WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM Refeicao WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM Refeicao WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM Refeicao";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM Refeicao WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(Refeicao entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getDescricao());
        stmt.setString(3, entity.getObjetivo());
        stmt.setBoolean(4, entity.getContemGluten());
        stmt.setBoolean(5, entity.getContemLactose());
        stmt.setDouble(6, entity.getCaloriasTotais());
        stmt.setDouble(7, entity.getCarboidratosTotais());
        stmt.setDouble(8, entity.getProteinasTotais());
        stmt.setDouble(9, entity.getSodioTotal());
        stmt.setDouble(10, entity.getGordurasTotais());

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
    protected Refeicao getEntityFromResultSet(ResultSet rs) throws SQLException {
        Refeicao refeicao = new Refeicao();
        refeicao.setId(rs.getInt("id"));
        refeicao.setNome(rs.getString("nome"));
        refeicao.setDescricao(rs.getString("descricao"));
        refeicao.setObjetivo(rs.getString("objetivo"));
        refeicao.setContemGluten(rs.getBoolean("contemGluten"));
        refeicao.setContemLactose(rs.getBoolean("contemLactose"));
        refeicao.setCaloriasTotais(rs.getDouble("calorias"));
        refeicao.setCarboidratosTotais(rs.getDouble("carboidratos"));
        refeicao.setProteinasTotais(rs.getDouble("proteinas"));
        refeicao.setSodioTotal(rs.getDouble("sodio"));
        refeicao.setGordurasTotais(rs.getDouble("gorduras"));
        return refeicao;
    }

    @Override
    protected Integer getEntityKey(Refeicao entity) {
        return entity.getId();
    }

    @Override
    public Optional<Refeicao> findByNome(String nome) {
        List<Refeicao> results = selectBy("nome", nome);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(0));
    }

    @Override
    public List<Refeicao> findByDiaId(Integer diaId) {
        String sql = "SELECT r.* FROM Refeicao r INNER JOIN DiaRefeicao dr ON r.id = dr.refeicaoId WHERE dr.diaId = ?";
        List<Refeicao> refeicoes = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, diaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                refeicoes.add(getEntityFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return refeicoes;
    }

    @Override
    public Integer create(Refeicao refeicao) {
        super.create(refeicao);
        Integer refeicaoId = getLastInsertId();

        for (Alimento alimento : refeicao.getAlimentos()) {
            saveRefeicaoAlimento(refeicaoId, alimento.getId());
        }
        return refeicaoId;
    }

    @Override
    public boolean update(Refeicao refeicao) {
        boolean updated = super.update(refeicao);
        deleteRefeicaoAlimentos(refeicao.getId());

        for (Alimento alimento : refeicao.getAlimentos()) {
            saveRefeicaoAlimento(refeicao.getId(), alimento.getId());
        }
        return updated;
    }

    private void saveRefeicaoAlimento(Integer refeicaoId, Integer alimentoId) {
        String sql = "INSERT INTO RefeicaoAlimento (refeicaoId, alimentoId) VALUES (?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, refeicaoId);
            stmt.setInt(2, alimentoId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteRefeicaoAlimentos(Integer refeicaoId) {
        String sql = "DELETE FROM RefeicaoAlimento WHERE refeicaoId = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, refeicaoId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
