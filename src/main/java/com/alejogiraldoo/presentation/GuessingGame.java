package com.alejogiraldoo.presentation;

import com.alejogiraldoo.infraestructure.actions.*;
import com.alejogiraldoo.infraestructure.services.RoundService;
import com.alejogiraldoo.infraestructure.services.StatsService;
import com.alejogiraldoo.infraestructure.services.TimerService;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;

import java.util.Objects;
import java.util.Scanner;

public class GuessingGame {

    private final Scanner sc = new Scanner(System.in);
    private final TimerService timerService;
    private final RoundService roundService;
    private final StatsService statsService;
    private final RoundInfo.Settings settings;

    public GuessingGame(
            TimerService timerService,
            RoundService roundService,
            StatsService statsService,
            RoundInfo.Settings settings
    ) {
        this.timerService = timerService;
        this.roundService = roundService;
        this.statsService = statsService;
        this.settings = settings;
        this.showGameRules();
    }

    public void start() {
        final RoundInfo roundInfo = new RoundInfo(this.settings);

        final GetDifficultyLevel getDifficultyLevel = new GetDifficultyLevel(sc, roundInfo);
        final GetChoice getChoice = new GetChoice(sc, timerService, roundInfo);

        final ValidateChoice validateChoice = new ValidateChoice(
                getChoice::execute,
                timerService,
                roundInfo
        );

        final SaveRound saveRound = new SaveRound(
                roundService,
                roundInfo
        );

        final ShowRoundResults showResults = new ShowRoundResults(statsService, roundInfo);

        getDifficultyLevel.setNext(getChoice);
        getChoice.setNext(validateChoice);
        validateChoice.setNext(saveRound);
        saveRound.setNext(showResults);

        getDifficultyLevel.execute();
        playAgain();
    }

    private void showGameRules() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.printf("I'm thinking of a number between %s and %s.\n", settings.startingNumber(), settings.endingNumber());
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

        } while (Objects.isNull(decision));

    }

}
