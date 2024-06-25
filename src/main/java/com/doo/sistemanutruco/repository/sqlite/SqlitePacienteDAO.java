package com.doo.sistemanutruco.repository.sqlite;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.util.AbstractTemplateSqlDAO;
import com.doo.sistemanutruco.usecases.paciente.PacienteDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class SqlitePacienteDAO extends AbstractTemplateSqlDAO<Paciente, String> implements PacienteDAO {

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Paciente (cpf, nome, dataNascimento, telefone, email, peso, altura, objetivo, porcentagemGordura, porcentagemMassaMagra, porcentagemMassaGorda, colesterolLDL, colesterolHDL, hipertenso, diabetico, celiaco, inativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Paciente SET nome = ?, dataNascimento = ?, telefone = ?, email = ?, peso = ?, altura = ?, objetivo = ?, porcentagemGordura = ?, porcentagemMassaMagra = ?, porcentagemMassaGorda = ?, colesterolLDL = ?, colesterolHDL = ?, hipertenso = ?, diabetico = ?, celiaco = ?, inativo = ? WHERE cpf = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM Paciente WHERE cpf = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM Paciente WHERE cpf = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM Paciente";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM Paciente WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(Paciente entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setDate(2, java.sql.Date.valueOf(entity.getDataNascimento()));
        stmt.setLong(3, entity.getTelefone());
        stmt.setString(4, entity.getEmail());
        stmt.setDouble(5, entity.getPeso());
        stmt.setDouble(6, entity.getAltura());
        stmt.setString(7, entity.getObjetivo());
        stmt.setInt(8, entity.getPorcentagemGordura());
        stmt.setInt(9, entity.getPorcentagemMassaMagra());
        stmt.setInt(10, entity.getPorcentagemMassaGorda());
        stmt.setInt(11, entity.getColesterolLDL());
        stmt.setInt(12, entity.getColesterolHDL());
        stmt.setBoolean(13, entity.getHipertenso());
        stmt.setBoolean(14, entity.getDiabetico());
        stmt.setBoolean(15, entity.getCeliaco());
        stmt.setBoolean(16, entity.getInativo());
        stmt.setString(17, entity.getCpf());
    }



    @Override
    protected void setKeyToPreparedStatement(String key, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, key);
    }

    @Override
    protected void setFilterToPreparedStatement(Object filter, PreparedStatement stmt) throws SQLException {
        if (filter instanceof String) {
            stmt.setString(1, (String) filter);
        } else {
            throw new IllegalArgumentException("Unsupported filter type: " + filter.getClass().getName());
        }
    }

    @Override
    protected Paciente getEntityFromResultSet(ResultSet rs) throws SQLException {
        String cpf = rs.getString("cpf");
        String nome = rs.getString("nome");
        LocalDate dataNascimento = rs.getDate("dataNascimento").toLocalDate();
        Long telefone = rs.getLong("telefone");
        String email = rs.getString("email");
        Double peso = rs.getDouble("peso");
        Double altura = rs.getDouble("altura");
        String objetivo = rs.getString("objetivo");
        Integer porcentagemGordura = rs.getInt("porcentagemGordura");
        Integer porcentagemMassaMagra = rs.getInt("porcentagemMassaMagra");
        Integer porcentagemMassaGorda = rs.getInt("porcentagemMassaGorda");
        Integer colesterolLDL = rs.getInt("colesterolLDL");
        Integer colesterolHDL = rs.getInt("colesterolHDL");
        boolean hipertenso = rs.getBoolean("hipertenso");
        boolean diabetico = rs.getBoolean("diabetico");
        boolean celiaco = rs.getBoolean("celiaco");
        boolean inativo = rs.getBoolean("inativo");

        Paciente paciente = new Paciente(cpf, nome, dataNascimento, telefone, email, peso, altura, objetivo, porcentagemGordura, porcentagemMassaMagra, porcentagemMassaGorda, colesterolLDL, colesterolHDL, hipertenso, diabetico, celiaco);
        paciente.setInativo(inativo);
        return paciente;
    }


    @Override
    protected String getEntityKey(Paciente paciente) {
        return paciente.getCpf();
    }

    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        return selectBy("cpf", cpf).stream().findFirst();
    }

    @Override
    public Optional<Paciente> findByNome(String nome) {
        return selectBy("nome", nome).stream().findFirst();
    }
}

