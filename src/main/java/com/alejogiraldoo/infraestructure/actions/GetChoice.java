package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.infraestructure.services.TimerService;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class GetChoice extends GameAction {

    @FunctionalInterface
    public interface ChoiceGetter {
        void getChoice();
    }

    private final Scanner sc;
    private final TimerService timerService;

    public GetChoice(
            Scanner sc,
            TimerService timerService,
            RoundInfo roundInfo
    ) {
        super(roundInfo);
        this.timerService = timerService;
        this.sc = sc;
    }

    @Override
    public void execute() {
        if (!this.timerService.isTimerActive()) this.timerService.startTimer();

        final int choice = getChoice();
        roundInfo.setChoice(choice);
        this.executeNext();
    }

    private int getChoice() {
        RoundInfo.Settings settings = roundInfo.getSettings();
        Integer choice = null;

        do {
            System.out.print("\nEnter your guess: ");
            try {
                choice = sc.nextInt();

                if (choice < settings.startingNumber() || choice > settings.endingNumber()) {
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.printf("Please insert a number between %s and %s \n", settings.startingNumber(), settings.endingNumber());
                choice = null;
                sc.nextLine();
            }
        } while (Objects.isNull(choice));

        return choice;
    }

}
