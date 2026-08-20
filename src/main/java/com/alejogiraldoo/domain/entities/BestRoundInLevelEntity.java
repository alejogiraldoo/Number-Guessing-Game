package com.alejogiraldoo.domain.entities;

import java.time.LocalTime;

public class BestRoundInLevelEntity {

    private String difficulty;
    private Integer bestRoundPrecisionPct;
    private LocalTime takenTime;

    public BestRoundInLevelEntity(String difficulty, Integer bestRoundPrecisionPct, LocalTime takenTime) {
        this.difficulty = difficulty;
        this.bestRoundPrecisionPct = bestRoundPrecisionPct;
        this.takenTime = takenTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getBestRoundPrecisionPct() {
        return bestRoundPrecisionPct;
    }

    public void setBestRoundPrecisionPct(Integer bestRoundPrecisionPct) {
        this.bestRoundPrecisionPct = bestRoundPrecisionPct;
    }

    public LocalTime getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(LocalTime takenTime) {
        this.takenTime = takenTime;
    }

    @Override
    public String toString() {
        return "BestRoundInLevelsEntity{" +
                "difficultyLevel='" + difficulty + '\'' +
                ", bestRoundPrecisionPct=" + bestRoundPrecisionPct +
                ", takenTime=" + takenTime +
                '}';
    }
}
