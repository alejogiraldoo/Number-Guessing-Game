package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.domain.entities.BestRoundInLevelEntity;
import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BestRoundInLevelDAO extends BaseDAO<BestRoundInLevelEntity> {

    public BestRoundInLevelDAO() throws CustomError {
        super();
    }

    public Set<BestRoundInLevelEntity> getRounds() throws CustomError {
        String sql = "SELECT * FROM best_round_in_levels";

        HashSet<BestRoundInLevelEntity> rounds = new HashSet<>();

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                BestRoundInLevelEntity bestRoundEntity = this.objectToEntity(result);
                rounds.add(bestRoundEntity);
            }

            return rounds;
        } catch (SQLException e) {
            throw new CustomError("Best rounds in levels couldn't be retrieved from the DB");
        }
    }

    protected BestRoundInLevelEntity objectToEntity(ResultSet result) throws SQLException {
        return new BestRoundInLevelEntity(
                result.getString("difficulty_level"),
                result.getInt("best_round_precision_pct"),
                !Objects.isNull(result.getTime("taken_time"))
                        ? result.getTime("taken_time").toLocalTime() : null
        );
    }

}
