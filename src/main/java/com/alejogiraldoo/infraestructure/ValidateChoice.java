package com.alejogiraldoo.infraestructure;

import com.alejogiraldoo.domain.entities.PlayerInfoEntity;

public class ValidateChoice extends GameAction {

    private final int guessingNumber;
    private int attempts = 0;

    public ValidateChoice(PlayerInfoEntity playerInfo) {
        super( playerInfo );
        this.guessingNumber = this.getGuessingNumber();
    }

    @Override
    public void execute() {
        final boolean guessed = this.isGuessed();
        final int chances = playerInfo.getDifficultyLevel().getChances();

        if( guessed && attempts <= chances ) return;

        if( !guessed && attempts < chances ) {
            this.executeNext();
            return;
        }

        System.out.println("\nYou runned out of chances...");
        System.out.println("GAME OVER!");
    }

    private int getGuessingNumber() {
        PlayerInfoEntity.Settings settings = playerInfo.getSettings();

        final int range = ( settings.getEndingNumber() - settings.getStartingNumber() ) + 1;
        return (int) ( ( range * Math.random() ) + settings.getStartingNumber() );
    }

    private boolean isGuessed() {
        final int choice = playerInfo.getChoice();
        attempts++;

        if( Integer.compare( choice, guessingNumber ) == 0 ) {
            System.out.printf("Congratulations! You guessed the correct number in %s attempts\n", attempts);
            return true;
        }

        if( Integer.compare( choice, guessingNumber ) < 0 ) {
            System.out.printf("Incorrect! The number is greater than %s.\n", choice);
            return false;
        }

        System.out.printf("Incorrect! The number is less than %s.\n", choice);
        return false;
    }
}
