package com.alejogiraldoo.config;

import com.alejogiraldoo.domain.errors.CustomError;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {

    private static HikariDataSource datasource;

    static {
        try {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl("jdbc:mysql://localhost:3307/number_guessing_game");
            config.setUsername("root");
            config.setPassword("123456");

            config.setMaximumPoolSize(2);

            config.setIdleTimeout(10000);
            config.setConnectionTimeout(30000);

            config.setLeakDetectionThreshold(15000);

            datasource = new HikariDataSource(config);
        } catch (HikariPool.PoolInitializationException e) {
            datasource = null;
        }
    }

    public static Connection getConnection() throws CustomError {
        if (datasource == null) throw new CustomError("DB connection couldn't be established");

        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            throw new CustomError("DB connection couldn't be established");
        }
    }

}
