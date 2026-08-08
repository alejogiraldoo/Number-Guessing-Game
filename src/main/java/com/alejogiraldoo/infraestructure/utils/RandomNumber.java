package com.alejogiraldoo.infraestructure.utils;

public class RandomNumber {

    @FunctionalInterface
    public interface RandomNumberProvider {
        int get(int min, int max);
    }

    public static int get(int min, int max) {
        final int range = (max - min) + 1;
        return (int) ((range * Math.random()) + min);
    }

}
