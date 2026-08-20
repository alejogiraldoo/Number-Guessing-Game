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

import java.util.Optional;

public class RoundService {

    private record RoundInfoFromDB(
            Optional<LevelEntity> levelEntity,
            Optional<ResultTypeEntity> resultTypeEntity
    ) {
    }

    public void saveRound(RoundInfo roundInfo) {
        var infoFromDB = this.getRoundInfoFromDB(roundInfo.getDifficulty(), roundInfo.getGameResult());

        if (infoFromDB.resultTypeEntity.isEmpty() || infoFromDB.levelEntity.isEmpty()) {
            System.out.println("Failed to retrieve the necessary info to save the round");
            return;
        }

        RoundEntity entity = this.createRoundEntity(
                infoFromDB.levelEntity.get(),
                infoFromDB.resultTypeEntity.get(),
                roundInfo
        );
        new RoundDAO().createRound(entity);
    }

    private RoundInfoFromDB getRoundInfoFromDB(
            EDifficultyLevel difficulty,
            EResultType gameResult
    ) {
        var levelEntity = new LevelDAO().getLevel(difficulty);
        var resultTypeEntity = new ResultTypeDAO().getType(gameResult);

        return new RoundInfoFromDB(
                levelEntity,
                resultTypeEntity
        );
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
