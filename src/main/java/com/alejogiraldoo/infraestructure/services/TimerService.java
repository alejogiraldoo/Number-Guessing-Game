package com.alejogiraldoo.infraestructure.services;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Objects;

public class TimerService {

    private Instant start;

    public void startTimer() {
        this.start = Instant.now();
    }

    public boolean isTimerActive() {
        return !Objects.isNull(start);
    }

    public LocalTime endTimer() {
        Instant end = Instant.now();

        Duration elapsed = Duration.between(start, end);
        LocalTime time = LocalTime.MIDNIGHT.plus(elapsed);

        this.start = null;
        return time;
    }

}
