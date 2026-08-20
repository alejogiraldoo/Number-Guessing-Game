package com.alejogiraldoo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static HikariDataSource datasource;

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3307/number_guessing_game");
        config.setUsername("root");
        config.setPassword("123456");

        config.setMaximumPoolSize(10);
        config.setMaximumPoolSize(2);

        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);

        config.setLeakDetectionThreshold(15000);

        datasource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

}
