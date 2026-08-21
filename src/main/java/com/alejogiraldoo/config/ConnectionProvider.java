package com.alejogiraldoo.config;

import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;

@FunctionalInterface
public interface ConnectionProvider {
    Connection getConnection() throws CustomError;
}
