package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.DBConnector;
import com.alejogiraldoo.domain.entities.BestRoundInLevelEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class BestRoundInLevelDAO {

    private final DBConnector dbConnector;

    public BestRoundInLevelDAO() throws SQLException {
        this.dbConnector = DBConnector.getInstance();
    }

    public Optional<Set<BestRoundInLevelEntity>> getRounds() {
        String sql = "SELECT * FROM best_round_in_levels";

        HashSet<BestRoundInLevelEntity> rounds = new HashSet<>();

        try (
                Connection connection = dbConnector.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                BestRoundInLevelEntity bestRoundEntity = this.objectToEntity(result);
                rounds.add(bestRoundEntity);
            }

            return Optional.of(rounds);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private BestRoundInLevelEntity objectToEntity(ResultSet result) throws SQLException {
        return new BestRoundInLevelEntity(
                result.getString("difficulty_level"),
                result.getInt("best_round_precision_pct"),
                !Objects.isNull(result.getTime("taken_time"))
                        ? result.getTime("taken_time").toLocalTime() : null
        );
    }

}
