package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.entities.BestRoundInLevelEntity;
import com.alejogiraldoo.domain.entities.LevelPrecisionRateEntity;
import com.alejogiraldoo.domain.entities.LevelStreakEntity;
import com.alejogiraldoo.domain.enums.EResultType;
import com.alejogiraldoo.domain.errors.CustomError;
import com.alejogiraldoo.infraestructure.services.StatsService;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;

import java.time.LocalTime;
import java.util.Set;

public class ShowRoundResults extends GameAction {

    private final StatsService statsService;

    public ShowRoundResults(StatsService statsService, RoundInfo roundInfo) {
        super(roundInfo);
        this.statsService = statsService;
    }

    @Override
    public void execute() {
        final EResultType gameResult = roundInfo.getGameResult();
        final LocalTime time = roundInfo.getTakenTime();
        final Integer guessingNumber = roundInfo.getGuessingNumber();

        if (gameResult.equals(EResultType.Win)) {
            System.out.printf("\nYou took %s secs to guess the number.\n", time.getSecond());
            this.showStats();
            return;
        }

        System.out.println("\nYou runned out of chances...");
        System.out.printf("The number is %s", guessingNumber);
        System.out.println("\nGAME OVER!\n");
        this.showStats();
    }

    private void showStats() {
        try {
            var stats = this.statsService.getAllStats();

            System.out.println("\n============== STATS ==============");
            if (!stats.bestRoundInLevels().isEmpty()) this.showBestRounds(stats.bestRoundInLevels());
            if (!stats.levelsPrecisionRate().isEmpty()) this.showLevelsPrecision(stats.levelsPrecisionRate());
            if (!stats.levelsStreak().isEmpty()) this.showLevelsStreak(stats.levelsStreak());

        } catch (CustomError e) {
            System.out.println(e.getMessage());
        }
    }

    private void showBestRounds(Set<BestRoundInLevelEntity> rounds) {
        System.out.println("\n------- BEST ROUND IN LEVELS -------");
        rounds.forEach(round -> {
            System.out.printf("\n[Dificulty: %s]", round.getDifficulty());
            System.out.printf("\nPrecision: %s%%", round.getBestRoundPrecisionPct());
            System.out.printf("\nTime: %s\n", round.getTakenTime());
        });
    }

    private void showLevelsPrecision(Set<LevelPrecisionRateEntity> precisions) {
        System.out.println("\n------- LEVELS PRECISION -------");
        precisions.forEach(precision -> {
            System.out.printf("\n[Dificulty: %s]", precision.getDifficultyLevel());
            System.out.printf("\nPrecision: %s%%\n", precision.getLevelPrecisionRate());
        });
    }

    private void showLevelsStreak(Set<LevelStreakEntity> streaks) {
        System.out.println("\n------- LEVELS STREAK -------");
        streaks.forEach(streak -> {
            System.out.printf("\n[Dificulty: %s]", streak.getDifficultyLevel());
            System.out.printf("\nMax Consecutive Wins: %s", streak.getMaxConsecutiveWins());
            System.out.printf("\nConsecutive Wins: %s\n", streak.getConsecutiveWins());
        });
    }
}
