package com.alejogiraldoo.domain.entities;

public class LevelStreakEntity {

    private String difficultyLevel;
    private int consecutiveWins;
    private int maxConsecutiveWins;

    public LevelStreakEntity(String difficultyLevel, int consecutiveWins, int maxConsecutiveWins) {
        this.difficultyLevel = difficultyLevel;
        this.consecutiveWins = consecutiveWins;
        this.maxConsecutiveWins = maxConsecutiveWins;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getConsecutiveWins() {
        return consecutiveWins;
    }

    public void setConsecutiveWins(int consecutiveWins) {
        this.consecutiveWins = consecutiveWins;
    }

    public int getMaxConsecutiveWins() {
        return maxConsecutiveWins;
    }

    public void setMaxConsecutiveWins(int maxConsecutiveWins) {
        this.maxConsecutiveWins = maxConsecutiveWins;
    }

    @Override
    public String toString() {
        return "LevelStreakEntity{" +
                "difficultyLevel='" + difficultyLevel + '\'' +
                ", consecutiveWins=" + consecutiveWins +
                ", maxConsecutiveWins=" + maxConsecutiveWins +
                '}';
    }
}
