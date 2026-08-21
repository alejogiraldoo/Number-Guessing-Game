package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.domain.entities.RoundEntity;
import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO extends BaseDAO<RoundEntity> {

    public RoundDAO() throws CustomError {
        super();
    }

    public void createRound(RoundEntity round) throws CustomError {
        String sql = "INSERT INTO rounds(level_id, result_type_id, attempts, taken_time, guessing_number) " +
                "VALUES (?,?,?,?,?)";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, round.getLevelId());
            statement.setLong(2, round.getResultType());
            statement.setInt(3, round.getAttempts());
            statement.setString(4, round.getTakenTime().toString());
            statement.setInt(5, round.getGuessingNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CustomError("Round couldn't be saved in history");
        }
    }

    @Override
    protected RoundEntity objectToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
