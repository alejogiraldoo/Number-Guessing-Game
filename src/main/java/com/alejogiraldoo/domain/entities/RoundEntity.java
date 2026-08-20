package com.alejogiraldoo.domain.entities;

import java.time.LocalTime;

public class RoundEntity {

    private Long roundId;
    private Long levelId;
    private Long resultType;
    private Integer attempts;
    private LocalTime takenTime;
    private Integer guessingNumber;

    public RoundEntity(Long roundId, Long levelId, Long resultType, Integer attempts, LocalTime takenTime, Integer guessingNumber) {
        this.roundId = roundId;
        this.levelId = levelId;
        this.resultType = resultType;
        this.attempts = attempts;
        this.takenTime = takenTime;
        this.guessingNumber = guessingNumber;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getResultType() {
        return resultType;
    }

    public void setResultType(Long resultType) {
        this.resultType = resultType;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
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

    public void setGuessingNumber(Integer guessingNumber) {
        this.guessingNumber = guessingNumber;
    }

    @Override
    public String toString() {
        return "RoundEntity{" +
                "roundId=" + roundId +
                ", levelId=" + levelId +
                ", resultType=" + resultType +
                ", attempts=" + attempts +
                ", takenTime=" + takenTime +
                ", guessingNumber=" + guessingNumber +
                '}';
    }
}
