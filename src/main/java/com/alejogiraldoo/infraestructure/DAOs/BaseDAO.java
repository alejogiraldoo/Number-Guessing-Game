package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.ConnectionProvider;
import com.alejogiraldoo.config.PoolConnection;
import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO<T> {
    private final ConnectionProvider connectionProvider = PoolConnection::getConnection;
    private final Connection connection;

    protected BaseDAO() throws CustomError {
        this.connection = this.connectionProvider.getConnection();
    }

    protected Connection getConnection() {
        return connection;
    }

    protected abstract T objectToEntity(ResultSet resultSet) throws SQLException;

}
