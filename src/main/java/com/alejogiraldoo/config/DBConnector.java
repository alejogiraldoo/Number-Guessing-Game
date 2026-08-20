package com.alejogiraldoo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3307/number_guessing_game";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static DBConnector instance;

    private final Connection connection;

    private DBConnector() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static synchronized DBConnector getInstance() throws SQLException {
        if (Objects.isNull(instance) || instance.connection.isClosed()) {
            instance = new DBConnector();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
