package com.doo.sistemanutruco.usecases.alimento;

import com.doo.sistemanutruco.entities.alimento.Alimento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ImportarAlimentosUseCase {
    private final AlimentoDAO alimentoDAO;

    public ImportarAlimentosUseCase(AlimentoDAO alimentoDAO){
        this.alimentoDAO = alimentoDAO;
    }

    public void importarAlimentosCSV(String pathCSV) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathCSV))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = bufferedReader.readLine()) != null ) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                if (linha.trim().isEmpty())
                    break;
                Alimento alimento = getAlimento(linha);
                alimentoDAO.create(alimento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Alimento getAlimento(String linha) {
        String[] valores = linha.split(",");

        String nome = valores[0];
        Double calorias = Double.parseDouble(valores[1]);
        Double carboidratos = Double.parseDouble(valores[2]);
        Double proteinas = Double.parseDouble(valores[3]);
        Double sodio = Double.parseDouble(valores[4]);
        Boolean gluten = valores[5].equalsIgnoreCase("Sim");
        Boolean lactose = valores[6].equalsIgnoreCase("Sim");
        Double gorduras = Double.parseDouble(valores[7]);

        return new Alimento(nome, calorias, carboidratos, proteinas, sodio, gluten, lactose, gorduras);
    }

    public List<Alimento> getAllAlimentos() {
        return alimentoDAO.findAll();
    }
}
