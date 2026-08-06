package com.alejogiraldoo.infraestructure.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class TimerService {

    private Instant start;

    public void startTimer() {
        this.start = Instant.now();
    }

    public boolean isTimerActive() {
        return !Objects.isNull( start );
    }

    public long endTimer() {
        Instant end = Instant.now();
        Duration elapsed = Duration.between( start, end );
        this.start = null;
        return elapsed.toSeconds();
    }

}
