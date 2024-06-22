package com.doo.sistemanutruco;

import com.doo.sistemanutruco.repository.util.DatabaseBuilder;
import com.doo.sistemanutruco.main.loader.WindowAlimentosManager;

public class App {
    public static void main(String[] args) {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
        WindowAlimentosManager.main(args);
    }
}