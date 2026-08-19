package com.alejogiraldoo.domain.entities;

import com.alejogiraldoo.domain.enums.EDifficultyLevel;

public final class PlayerInfo {

    private EDifficultyLevel difficultyLevel;
    private Integer choice;
    private Settings settings;

    public static class Settings {
        private int startingNumber;
        private int endingNumber;

        public Settings(int startingNumber, int endingNumber) {
            this.startingNumber = startingNumber;
            this.endingNumber = endingNumber;
        }

        public int getStartingNumber() {
            return startingNumber;
        }

        public int getEndingNumber() {
            return endingNumber;
        }
    }

    public PlayerInfo(Settings settings) {
        this.settings = settings;
    }

    public EDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(EDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    public Settings getSettings() {
        return settings;
    }
}
