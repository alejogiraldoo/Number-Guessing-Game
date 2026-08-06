package com.alejogiraldoo.infraestructure;

import com.alejogiraldoo.domain.entities.PlayerInfoEntity;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class GetChoice extends GameAction {

    private final Scanner sc;

    public GetChoice(Scanner sc, PlayerInfoEntity playerInfo) {
        super( playerInfo );
        this.sc = sc;
    }

    @Override
    public void execute() {
        final int choice = getChoice();
        playerInfo.setChoice( choice );
        this.executeNext();
    }

    private int getChoice() {
        PlayerInfoEntity.Settings settings = playerInfo.getSettings();
        Integer choice = null;

        do {
            System.out.print("\nEnter your guess: ");
            try {
                choice = sc.nextInt();

                if (choice < settings.getStartingNumber() || choice > settings.getEndingNumber()) {
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.printf("Please insert a number between %s and %s \n", settings.getStartingNumber(), settings.getEndingNumber());
                choice = null;
                sc.nextLine();
            }
        } while (Objects.isNull(choice));

        return choice;
    }

}
