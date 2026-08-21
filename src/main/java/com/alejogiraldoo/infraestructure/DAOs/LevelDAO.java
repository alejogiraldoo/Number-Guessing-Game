package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.domain.entities.LevelEntity;
import com.alejogiraldoo.domain.enums.EDifficultyLevel;
import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LevelDAO extends BaseDAO<LevelEntity> {

    public LevelDAO() throws CustomError {
        super();
    }

    public Optional<LevelEntity> getLevel(EDifficultyLevel difficulty) throws CustomError {
        String sql = "SELECT * FROM levels WHERE name = ? AND chances = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, difficulty.name());
            statement.setInt(2, difficulty.getChances());

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) return Optional.empty();

                LevelEntity levelEntity = this.objectToEntity(result);
                return Optional.of(levelEntity);
            }
        } catch (SQLException e) {
            throw new CustomError("Level couldn't be retrieved from the DB");
        }
    }

    protected LevelEntity objectToEntity(ResultSet result) throws SQLException {
        return new LevelEntity(
                result.getLong("level_id"),
                result.getString("name"),
                result.getInt("chances")
        );
    }

}
