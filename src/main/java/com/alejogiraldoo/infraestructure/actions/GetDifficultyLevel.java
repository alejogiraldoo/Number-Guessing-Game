package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.entities.PlayerEntity;
import com.alejogiraldoo.domain.enums.EDifficultyLevel;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class GetDifficultyLevel extends GameAction {

    private final Scanner sc;
    private final EDifficultyLevel[] difficultyLevels = EDifficultyLevel.values();

    public GetDifficultyLevel( Scanner sc, PlayerEntity personInfo ) {
        super( personInfo );
        this.sc = sc;
    }

    @Override
    public void execute() {
        this.showMessage();

        final EDifficultyLevel difficulty = this.getDifficulty();
        playerInfo.setDifficultyLevel( difficulty );

        this.executeNext();
    }

    private void showMessage() {
        System.out.println("\nPlease select the difficulty level:");

        for (int i = 0; i < difficultyLevels.length; i++) {
            final EDifficultyLevel level = difficultyLevels[i];
            final int chances = difficultyLevels[i].getChances();
            System.out.printf("%s. %s ( %s chances ) %n", i + 1, level, chances);
        }
    }

    private EDifficultyLevel getDifficulty() {
        Integer levelOption = null;

        do {
            System.out.print("\nEnter your choice: ");
            try {
                levelOption = sc.nextInt();

                if ( levelOption < 1 || levelOption > difficultyLevels.length ) {
                    throw new IllegalArgumentException();
                }
            } catch ( InputMismatchException | IllegalArgumentException e ) {
                System.out.println("Please insert a number that matches the level options");
                levelOption = null;
                sc.nextLine();
            }
        } while(Objects.isNull(levelOption));

        EDifficultyLevel difficulty = difficultyLevels[ levelOption - 1 ];
        System.out.printf("\nGreat! you have selected the %s difficulty level.\n", difficulty);
        System.out.println("Let's start the game!");

        return difficultyLevels[ levelOption - 1 ];
    }
}
