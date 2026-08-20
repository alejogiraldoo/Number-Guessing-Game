package com.alejogiraldoo.infraestructure.services;

import com.alejogiraldoo.domain.entities.BestRoundInLevelEntity;
import com.alejogiraldoo.domain.entities.LevelPrecisionRateEntity;
import com.alejogiraldoo.domain.entities.LevelStreakEntity;
import com.alejogiraldoo.infraestructure.DAOs.BestRoundInLevelDAO;
import com.alejogiraldoo.infraestructure.DAOs.LevelPrecisionRateDAO;
import com.alejogiraldoo.infraestructure.DAOs.LevelStreakDAO;

import java.util.Optional;
import java.util.Set;

public class StatsService {

    public record Stats(
            Optional<Set<BestRoundInLevelEntity>> bestRoundInLevels,
            Optional<Set<LevelPrecisionRateEntity>> levelsPrecisionRate,
            Optional<Set<LevelStreakEntity>> levelsStreak
    ) {
    }

    public Stats getAllStats() {
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
