package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.ConnectionPool;
import com.alejogiraldoo.domain.entities.RoundEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoundDAO {

    public void createRound(RoundEntity round) {
        String sql = "INSERT INTO rounds(level_id, result_type_id, attempts, taken_time, guessing_number) " +
                "VALUES (?,?,?,?,?)";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, round.getLevelId());
            statement.setLong(2, round.getResultType());
            statement.setInt(3, round.getAttempts());
            statement.setString(4, round.getTakenTime().toString());
            statement.setInt(5, round.getGuessingNumber());

            statement.executeUpdate();
        } catch (SQLException | ExceptionInInitializerError | NoClassDefFoundError e) {
            System.out.println(e.getMessage());
        }
    }
}
