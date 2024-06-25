package com.doo.sistemanutruco.repository.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public DatabaseBuilder() {
        buildTables();
    }

    public void buildDatabaseIfMissing() {
        if (!isDatabaseAvailable()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseAvailable() {
        return Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement stmt = ConnectionFactory.createStatement()) {
            stmt.addBatch(createAlimentoTableSql());
            stmt.addBatch(createRefeicaoTableSql());
            stmt.addBatch(createDiaTableSql());
            stmt.addBatch(createDietaTableSql());
            stmt.addBatch(createPacienteTableSql());
            stmt.addBatch(createRefeicaoAlimentoTableSql());
            stmt.addBatch(createDietaDiaTableSql());
            stmt.addBatch(createPacienteDietaTableSql());
            stmt.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createAlimentoTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Alimento (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("calorias DOUBLE, \n");
        builder.append("carboidratos DOUBLE, \n");
        builder.append("proteinas DOUBLE, \n");
        builder.append("sodio DOUBLE, \n");
        builder.append("gluten BOOLEAN, \n");
        builder.append("lactose BOOLEAN, \n");
        builder.append("gorduras DOUBLE \n");
        builder.append(");\n");
        return builder.toString();
    }

    private String createRefeicaoTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Refeicao (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("descricao TEXT, \n");
        builder.append("objetivo TEXT, \n");
        builder.append("contemGluten BOOLEAN, \n");
        builder.append("contemLactose BOOLEAN, \n");
        builder.append("calorias DOUBLE, \n");
        builder.append("carboidratos DOUBLE, \n");
        builder.append("proteinas DOUBLE, \n");
        builder.append("sodio DOUBLE, \n");
        builder.append("gorduras DOUBLE \n");
        builder.append(");\n");
        return builder.toString();
    }

    private String createDiaTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Dia (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("diaDaSemana TEXT NOT NULL, \n");  // Usando TEXT para DayOfWeek
        builder.append("inativo BOOLEAN, \n");
        builder.append("calorias DOUBLE, \n");
        builder.append("carboidratos DOUBLE, \n");
        builder.append("proteinas DOUBLE, \n");
        builder.append("sodio DOUBLE, \n");
        builder.append("gorduras DOUBLE \n");
        builder.append(");\n");
        return builder.toString();
    }

    private String createDietaTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Dieta (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("objetivo TEXT, \n");
        builder.append("inativo BOOLEAN, \n");
        builder.append("calorias DOUBLE, \n");
        builder.append("carboidratos DOUBLE, \n");
        builder.append("proteinas DOUBLE, \n");
        builder.append("sodio DOUBLE, \n");
        builder.append("contemGluten BOOLEAN, \n");
        builder.append("contemLactose BOOLEAN, \n");
        builder.append("gorduras DOUBLE \n");
        builder.append(");\n");
        return builder.toString();
    }

    private String createPacienteTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Paciente (\n");
        builder.append("cpf TEXT PRIMARY KEY, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("dataNascimento DATE, \n");
        builder.append("telefone LONG, \n");
        builder.append("email TEXT, \n");
        builder.append("peso DOUBLE, \n");
        builder.append("altura DOUBLE, \n");
        builder.append("objetivo TEXT, \n");
        builder.append("porcentagemGordura INTEGER, \n");
        builder.append("porcentagemMassaMagra INTEGER, \n");
        builder.append("porcentagemMassaGorda INTEGER, \n");
        builder.append("colesterolLDL INTEGER, \n");
        builder.append("colesterolHDL INTEGER, \n");
        builder.append("hipertenso BOOLEAN, \n");
        builder.append("diabetico BOOLEAN, \n");
        builder.append("celiaco BOOLEAN, \n");
        builder.append("inativo BOOLEAN \n");
        builder.append(");\n");
        return builder.toString();
    }


    private String createRefeicaoAlimentoTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS RefeicaoAlimento (\n");
        builder.append("refeicaoId INTEGER NOT NULL, \n");
        builder.append("alimentoId INTEGER NOT NULL, \n");
        builder.append("PRIMARY KEY (refeicaoId, alimentoId), \n");
        builder.append("FOREIGN KEY (refeicaoId) REFERENCES Refeicao(id), \n");
        builder.append("FOREIGN KEY (alimentoId) REFERENCES Alimento(id) \n");
        builder.append(");\n");
        return builder.toString();
    }

    private String createDiaRefeicaoTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS DiaRefeicao (\n");
        builder.append("diaId INTEGER NOT NULL, \n");
        builder.append("refeicaoId INTEGER NOT NULL, \n");
        builder.append("PRIMARY KEY (diaId, refeicaoId), \n");
        builder.append("FOREIGN KEY (diaId) REFERENCES Dia(id), \n");
        builder.append("FOREIGN KEY (refeicaoId) REFERENCES Refeicao(id) \n");
        builder.append(");\n");
        return builder.toString();
    }

    private String createDietaDiaTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS DietaDia (\n");
        builder.append("dietaId INTEGER NOT NULL, \n");
        builder.append("diaId INTEGER NOT NULL, \n");
        builder.append("PRIMARY KEY (dietaId, diaId), \n");
        builder.append("FOREIGN KEY (dietaId) REFERENCES Dieta(id), \n");
        builder.append("FOREIGN KEY (diaId) REFERENCES Dia(id) \n");
        builder.append(");\n");
        return builder.toString();
    }

    private String createPacienteDietaTableSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS PacienteDieta (\n");
        builder.append("pacienteCpf TEXT NOT NULL, \n");
        builder.append("dietaId INTEGER NOT NULL, \n");
        builder.append("dataIni DATE, \n");
        builder.append("dataFim DATE, \n");
        builder.append("PRIMARY KEY (pacienteCpf, dietaId), \n");
        builder.append("FOREIGN KEY (pacienteCpf) REFERENCES Paciente(cpf), \n");
        builder.append("FOREIGN KEY (dietaId) REFERENCES Dieta(id) \n");
        builder.append(");\n");
        return builder.toString();
    }
}