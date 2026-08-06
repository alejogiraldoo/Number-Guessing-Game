package com.alejogiraldoo.domain.enums;

public enum EDifficultyLevel {
    EASY(10),
    MEDIUM(5),
    HARD(3);

    private int chances;

    EDifficultyLevel(int chances) {
        this.chances = chances;
    }

    public int getChances() {
        return chances;
    }
}
