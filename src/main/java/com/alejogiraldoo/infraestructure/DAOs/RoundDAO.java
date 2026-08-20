package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.DBConnector;
import com.alejogiraldoo.domain.entities.RoundEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoundDAO {

    private final DBConnector dbConnector;

    public RoundDAO() throws SQLException {
        this.dbConnector = DBConnector.getInstance();
    }

    public void createRound(RoundEntity round) {
        String sql = "INSERT INTO rounds(level_id, result_type_id, attempts, taken_time, guessing_number) " +
                "VALUES (?,?,?,?,?)";

        try (
                Connection connection = dbConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, round.getLevelId());
            statement.setLong(2, round.getResultType());
            statement.setInt(3, round.getAttempts());
            statement.setString(4, round.getTakenTime().toString());
            statement.setInt(5, round.getGuessingNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
