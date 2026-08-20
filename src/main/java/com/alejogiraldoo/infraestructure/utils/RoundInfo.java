package com.alejogiraldoo.infraestructure.utils;

import com.alejogiraldoo.domain.enums.EDifficultyLevel;
import com.alejogiraldoo.domain.enums.EResultType;

import java.time.LocalTime;

public final class RoundInfo {

    private EDifficultyLevel difficulty;
    private Integer choice;
    private Integer guessingNumber;
    private EResultType gameResult;
    private int attempts;
    private LocalTime takenTime;
    private Settings settings;

    public record Settings(
            int startingNumber,
            int endingNumber
    ) {
    }

    public RoundInfo(Settings settings) {
        this.settings = settings;
    }

    public EDifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EDifficultyLevel difficulty) {
        this.difficulty = difficulty;
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

    public LocalTime getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(LocalTime takenTime) {
        this.takenTime = takenTime;
    }

    public Integer getGuessingNumber() {
        return guessingNumber;
    }

    public EResultType getGameResult() {
        return gameResult;
    }

    public void setGameResult(EResultType gameResult) {
        this.gameResult = gameResult;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setGuessingNumber(Integer guessingNumber) {
        this.guessingNumber = guessingNumber;
    }
}
