package com.alejogiraldoo;

import com.alejogiraldoo.domain.entities.PlayerEntity;
import com.alejogiraldoo.infraestructure.services.TimerService;
import com.alejogiraldoo.presentation.GuessingGame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        final PlayerEntity.Settings settings = new PlayerEntity.Settings( 1, 100 );
        final TimerService timerService = new TimerService();

        new GuessingGame( timerService, settings ).start();

    }
}