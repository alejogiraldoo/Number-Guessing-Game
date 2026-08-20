package com.alejogiraldoo.domain.entities;

public class LevelPrecisionRateEntity {

    private String difficultyLevel;
    private Integer levelPrecisionRate;

    public LevelPrecisionRateEntity(String levelName, Integer levelPrecisionRate) {
        this.difficultyLevel = levelName;
        this.levelPrecisionRate = levelPrecisionRate;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getLevelPrecisionRate() {
        return levelPrecisionRate;
    }

    public void setLevelPrecisionRate(Integer levelPrecisionRate) {
        this.levelPrecisionRate = levelPrecisionRate;
    }

    @Override
    public String toString() {
        return "LevelPrecisionRateEntity{" +
                "levelName='" + difficultyLevel + '\'' +
                ", levelPrecisionRate=" + levelPrecisionRate +
                '}';
    }
}
