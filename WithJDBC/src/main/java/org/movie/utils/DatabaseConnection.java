package org.movie.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Artur Tomeyan
 * @date 23/08/2022
 */
public final class DatabaseConnection {

    private static final Configuration CONFIGURATION = Configuration.getConfiguration();
    private static DatabaseConnection databaseConnection;
    private final Connection connection;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final String DRIVER;

    static {
        URL = CONFIGURATION.getValue("database.url");
        USERNAME = CONFIGURATION.getValue("database.userName");
        PASSWORD = CONFIGURATION.getValue("database.password");
        DRIVER = CONFIGURATION.getValue("jdbc.driver");
    }

    private DatabaseConnection() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnection getDatabaseConnection() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }

        return databaseConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}