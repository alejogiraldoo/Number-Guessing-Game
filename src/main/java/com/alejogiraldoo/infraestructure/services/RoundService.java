package com.alejogiraldoo.infraestructure.services;

import com.alejogiraldoo.domain.entities.LevelEntity;
import com.alejogiraldoo.domain.entities.ResultTypeEntity;
import com.alejogiraldoo.domain.entities.RoundEntity;
import com.alejogiraldoo.domain.enums.EDifficultyLevel;
import com.alejogiraldoo.domain.enums.EResultType;
import com.alejogiraldoo.infraestructure.DAOs.LevelDAO;
import com.alejogiraldoo.infraestructure.DAOs.ResultTypeDAO;
import com.alejogiraldoo.infraestructure.DAOs.RoundDAO;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;

import java.sql.SQLException;
import java.util.Optional;

public class RoundService {

    private record RoundInfoFromDB(
            Optional<LevelEntity> levelEntity,
            Optional<ResultTypeEntity> resultTypeEntity
    ) {
    }

    public void saveRound(RoundInfo roundInfo) {
        this.getRoundInfoFromDB(roundInfo.getDifficulty(), roundInfo.getGameResult()).ifPresentOrElse(
                infoFromDB -> {
                    if (infoFromDB.resultTypeEntity.isEmpty() || infoFromDB.levelEntity.isEmpty()) {
                        System.out.println("Failed to retrieve the necessary info to save the round");
                        return;
                    }

                    try {
                        RoundEntity entity = this.createRoundEntity(
                                infoFromDB.levelEntity.get(),
                                infoFromDB.resultTypeEntity.get(),
                                roundInfo
                        );
                        new RoundDAO().createRound(entity);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                },
                () -> System.out.println("ERROR: Round couldn't be saved in history...")
        );
    }

    private Optional<RoundInfoFromDB> getRoundInfoFromDB(
            EDifficultyLevel difficulty,
            EResultType gameResult
    ) {
        try {
            var levelEntity = new LevelDAO().getLevel(difficulty);
            var resultTypeEntity = new ResultTypeDAO().getType(gameResult);

            return Optional.of(new RoundInfoFromDB(
                    levelEntity,
                    resultTypeEntity
            ));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private RoundEntity createRoundEntity(
            LevelEntity levelEntity,
            ResultTypeEntity resultTypeEntity,
            RoundInfo roundInfo
    ) {
        return new RoundEntity(
                null,
                levelEntity.getLevelId(),
                resultTypeEntity.getResultTypeId(),
                roundInfo.getAttempts(),
                roundInfo.getTakenTime(),
                roundInfo.getGuessingNumber()
        );
    }

}
