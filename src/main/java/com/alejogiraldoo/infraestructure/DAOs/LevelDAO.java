package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.DBConnector;
import com.alejogiraldoo.domain.entities.LevelEntity;
import com.alejogiraldoo.domain.enums.EDifficultyLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LevelDAO {

    private final DBConnector dbConnector;

    public LevelDAO() throws SQLException {
        this.dbConnector = DBConnector.getInstance();
    }

    public Optional<LevelEntity> getLevel(EDifficultyLevel difficulty) {
        String sql = "SELECT * FROM levels WHERE name = ? AND chances = ?";

        try (
                Connection connection = dbConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, difficulty.name());
            statement.setInt(2, difficulty.getChances());

            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                result.close();
                return Optional.empty();
            }

            LevelEntity levelEntity = this.objectToEntity(result);
            result.close();
            return Optional.of(levelEntity);
        } catch (SQLException e) {
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
