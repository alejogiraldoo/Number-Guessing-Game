package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.ConnectionPool;
import com.alejogiraldoo.domain.entities.LevelPrecisionRateEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LevelPrecisionRateDAO {

    public Optional<Set<LevelPrecisionRateEntity>> getPrecisions() {
        String sql = "SELECT * FROM levels_precision_rate";

        HashSet<LevelPrecisionRateEntity> precisions = new HashSet<>();

        try (
                Connection connection = ConnectionPool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                LevelPrecisionRateEntity levelPrecisionEntity = this.objectToEntity(result);
                precisions.add(levelPrecisionEntity);
            }

            return Optional.of(precisions);
        } catch (SQLException | ExceptionInInitializerError | NoClassDefFoundError e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private LevelPrecisionRateEntity objectToEntity(ResultSet result) throws SQLException {
        return new LevelPrecisionRateEntity(
                result.getString("difficulty_level"),
                result.getInt("level_precision_pct")
        );
    }

}
