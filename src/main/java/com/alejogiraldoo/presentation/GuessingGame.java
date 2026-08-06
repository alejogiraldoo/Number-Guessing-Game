package com.alejogiraldoo.presentation;

import com.alejogiraldoo.domain.entities.PlayerInfoEntity;
import com.alejogiraldoo.infraestructure.GetChoice;
import com.alejogiraldoo.infraestructure.GetDifficultyLevel;
import com.alejogiraldoo.infraestructure.ValidateChoice;

import java.util.Scanner;

public class GuessingGame {

    private final Scanner sc = new Scanner(System.in);
    private final PlayerInfoEntity playerInfo;

    public GuessingGame(PlayerInfoEntity.Settings settings) {
        this.playerInfo = new PlayerInfoEntity( settings );
    }

    public void start() {
        this.showGameRules();

        final GetDifficultyLevel getDifficultyLevel = new GetDifficultyLevel( sc, playerInfo );
        final GetChoice getChoice = new GetChoice( sc, playerInfo );
        final ValidateChoice validateChoice = new ValidateChoice( playerInfo );

        getDifficultyLevel.setNext( getChoice );
        getChoice.setNext( validateChoice );
        validateChoice.setNext( getChoice );

        getDifficultyLevel.execute();
    }

    private void showGameRules() {
        final PlayerInfoEntity.Settings settings = this.playerInfo.getSettings();

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.printf("I'm thinking of a number between %s and %s.\n", settings.getStartingNumber(), settings.getEndingNumber());
        System.out.println("You have a certain amount of chances to guess the correct number.\n");
    }

}
