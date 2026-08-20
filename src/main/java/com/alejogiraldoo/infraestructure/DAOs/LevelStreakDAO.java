package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.DBConnector;
import com.alejogiraldoo.domain.entities.LevelStreakEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LevelStreakDAO {

    private final DBConnector dbConnector;

    public LevelStreakDAO() throws SQLException {
        this.dbConnector = DBConnector.getInstance();
    }

    public Optional<Set<LevelStreakEntity>> getStreaks() {
        String sql = "SELECT * FROM levels_streak";

        HashSet<LevelStreakEntity> streaks = new HashSet<>();

        try (
                Connection connection = dbConnector.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                LevelStreakEntity levelStreakEntity = this.objectToEntity(result);
                streaks.add(levelStreakEntity);
            }

            return Optional.of(streaks);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private LevelStreakEntity objectToEntity(ResultSet result) throws SQLException {
        return new LevelStreakEntity(
                result.getString("difficulty_level"),
                result.getInt("consecutive_wins"),
                result.getInt("max_consecutive_wins")
        );
    }
}
