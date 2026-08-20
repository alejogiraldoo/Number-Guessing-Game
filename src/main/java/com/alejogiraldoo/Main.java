package com.alejogiraldoo;

import com.alejogiraldoo.infraestructure.services.RoundService;
import com.alejogiraldoo.infraestructure.services.StatsService;
import com.alejogiraldoo.infraestructure.services.TimerService;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;
import com.alejogiraldoo.presentation.GuessingGame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        final RoundInfo.Settings settings = new RoundInfo.Settings(1, 100);
        final TimerService timerService = new TimerService();
        final RoundService roundService = new RoundService();
        final StatsService statsService = new StatsService();

        new GuessingGame(
                timerService,
                roundService,
                statsService,
                settings
        ).start();
    }
}