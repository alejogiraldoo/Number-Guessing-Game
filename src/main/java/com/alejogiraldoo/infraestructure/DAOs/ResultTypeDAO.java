package com.alejogiraldoo.infraestructure.DAOs;

import com.alejogiraldoo.domain.entities.ResultTypeEntity;
import com.alejogiraldoo.domain.enums.EResultType;
import com.alejogiraldoo.domain.errors.CustomError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ResultTypeDAO extends BaseDAO<ResultTypeEntity> {


    public ResultTypeDAO() throws CustomError {
        super();
    }

    public Optional<ResultTypeEntity> getType(EResultType type) throws CustomError {
        String sql = "SELECT * FROM result_types WHERE name = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, type.name());

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    return Optional.empty();
                }

                ResultTypeEntity typeEntity = this.objectToEntity(result);
                return Optional.of(typeEntity);
            }
        } catch (SQLException e) {
            throw new CustomError("Result type couldn't be retrieved from the DB");
        }
    }

    protected ResultTypeEntity objectToEntity(ResultSet result) throws SQLException {
        return new ResultTypeEntity(
                result.getLong("result_type_id"),
                result.getString("name")
        );
    }

}
