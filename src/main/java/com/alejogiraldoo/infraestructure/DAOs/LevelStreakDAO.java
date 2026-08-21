package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.domain.entities.LevelStreakEntity;
import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class LevelStreakDAO extends BaseDAO<LevelStreakEntity> {

    public LevelStreakDAO() throws CustomError {
        super();
    }

    public Set<LevelStreakEntity> getStreaks() throws CustomError {
        String sql = "SELECT * FROM levels_streak";

        HashSet<LevelStreakEntity> streaks = new HashSet<>();

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                LevelStreakEntity levelStreakEntity = this.objectToEntity(result);
                streaks.add(levelStreakEntity);
            }

            return streaks;
        } catch (SQLException e) {
            throw new CustomError("Levels Streak couldn't be retrieved from the DB");
        }
    }

    protected LevelStreakEntity objectToEntity(ResultSet result) throws SQLException {
        return new LevelStreakEntity(
                result.getString("difficulty_level"),
                result.getInt("consecutive_wins"),
                result.getInt("max_consecutive_wins")
        );
    }
}
