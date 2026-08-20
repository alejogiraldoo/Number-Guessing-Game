package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.ConnectionPool;
import com.alejogiraldoo.domain.entities.LevelEntity;
import com.alejogiraldoo.domain.enums.EDifficultyLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LevelDAO {

    public Optional<LevelEntity> getLevel(EDifficultyLevel difficulty) {
        String sql = "SELECT * FROM levels WHERE name = ? AND chances = ?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, difficulty.name());
            statement.setInt(2, difficulty.getChances());

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    return Optional.empty();
                }

                LevelEntity levelEntity = this.objectToEntity(result);
                return Optional.of(levelEntity);
            }
        } catch (SQLException | ExceptionInInitializerError | NoClassDefFoundError e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private LevelEntity objectToEntity(ResultSet result) throws SQLException {
        return new LevelEntity(
                result.getLong("level_id"),
                result.getString("name"),
                result.getInt("chances")
        );
    }

}
