package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.config.DBConnector;
import com.alejogiraldoo.domain.entities.ResultTypeEntity;
import com.alejogiraldoo.domain.enums.EResultType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ResultTypeDAO {

    private final DBConnector dbConnector;

    public ResultTypeDAO() throws SQLException {
        this.dbConnector = DBConnector.getInstance();
    }

    public Optional<ResultTypeEntity> getType(EResultType type) {
        String sql = "SELECT * FROM result_types WHERE name = ?";

        try (
                Connection connection = dbConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, type.name());

            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                result.close();
                return Optional.empty();
            }

            ResultTypeEntity typeEntity = this.objectToEntity(result);
            result.close();
            return Optional.of(typeEntity);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private ResultTypeEntity objectToEntity(ResultSet result) throws SQLException {
        return new ResultTypeEntity(
                result.getLong("result_type_id"),
                result.getString("name")
        );
    }

}
