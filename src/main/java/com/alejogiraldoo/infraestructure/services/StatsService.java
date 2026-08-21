package com.alejogiraldoo.infraestructure.services;

import com.alejogiraldoo.domain.entities.BestRoundInLevelEntity;
import com.alejogiraldoo.domain.entities.LevelPrecisionRateEntity;
import com.alejogiraldoo.domain.entities.LevelStreakEntity;
import com.alejogiraldoo.domain.errors.CustomError;
import com.alejogiraldoo.infraestructure.DAOs.BestRoundInLevelDAO;
import com.alejogiraldoo.infraestructure.DAOs.LevelPrecisionRateDAO;
import com.alejogiraldoo.infraestructure.DAOs.LevelStreakDAO;

import java.util.Set;

public class StatsService {

    public record Stats(
            Set<BestRoundInLevelEntity> bestRoundInLevels,
            Set<LevelPrecisionRateEntity> levelsPrecisionRate,
            Set<LevelStreakEntity> levelsStreak
    ) {
    }

    public Stats getAllStats() throws CustomError {
        var bestRoundInLevels = new BestRoundInLevelDAO().getRounds();

        var levelsPrecisionRate = new LevelPrecisionRateDAO().getPrecisions();

        var levelsStreak = new LevelStreakDAO().getStreaks();

        return new Stats(
                bestRoundInLevels,
                levelsPrecisionRate,
                levelsStreak
        );
    }

}
