package com.alejogiraldoo.presentation;

import com.alejogiraldoo.domain.entities.PlayerInfoEntity;
import com.alejogiraldoo.infraestructure.actions.GetChoice;
import com.alejogiraldoo.infraestructure.actions.GetDifficultyLevel;
import com.alejogiraldoo.infraestructure.actions.ValidateChoice;
import com.alejogiraldoo.infraestructure.services.TimerService;

import java.util.Objects;
import java.util.Scanner;

public class GuessingGame {

    private final Scanner sc = new Scanner(System.in);
    private final TimerService timerService;
    private final PlayerInfoEntity.Settings settings;

    public GuessingGame(
            TimerService timerService,
            PlayerInfoEntity.Settings settings
    ) {
        this.timerService = timerService;
        this.settings = settings;
        this.showGameRules();
    }

    public void start() {
        final PlayerInfoEntity playerInfo = new PlayerInfoEntity( this.settings );

        final GetDifficultyLevel getDifficultyLevel = new GetDifficultyLevel( sc, playerInfo );
        final GetChoice getChoice = new GetChoice( sc, timerService, playerInfo );
        final ValidateChoice validateChoice = new ValidateChoice( timerService, playerInfo );

        getDifficultyLevel.setNext( getChoice );
        getChoice.setNext( validateChoice );
        validateChoice.setNext( getChoice );

        getDifficultyLevel.execute();
        playAgain();
    }

    private void showGameRules() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.printf("I'm thinking of a number between %s and %s.\n", settings.getStartingNumber(), settings.getEndingNumber());
        System.out.println("You have a certain amount of chances to guess the correct number.");
    }

    private void playAgain() {
        System.out.println("\nDo you want to play again?");

        String decision;

        do {
            System.out.print("Enter YESS or NO: ");
            decision = sc.next().toUpperCase();

            if (decision.equals("NO")) return;

            if (decision.equals("YESS")) {
                start();
                return;
            }

            decision = null;
            sc.nextLine();

        } while(Objects.isNull(decision));

    }

}
