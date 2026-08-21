package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.domain.entities.LevelPrecisionRateEntity;
import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class LevelPrecisionRateDAO extends BaseDAO<LevelPrecisionRateEntity> {

    public LevelPrecisionRateDAO() throws CustomError {
        super();
    }

    public Set<LevelPrecisionRateEntity> getPrecisions() throws CustomError {
        String sql = "SELECT * FROM levels_precision_rate";

        HashSet<LevelPrecisionRateEntity> precisions = new HashSet<>();

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                LevelPrecisionRateEntity levelPrecisionEntity = this.objectToEntity(result);
                precisions.add(levelPrecisionEntity);
            }

            return precisions;
        } catch (SQLException e) {
            throw new CustomError("Levels precision rate couldn't be retrieved from the DB");
        }
    }

    protected LevelPrecisionRateEntity objectToEntity(ResultSet result) throws SQLException {
        return new LevelPrecisionRateEntity(
                result.getString("difficulty_level"),
                result.getInt("level_precision_pct")
        );
    }

}
