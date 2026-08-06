package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.entities.PlayerInfoEntity;
import com.alejogiraldoo.infraestructure.services.TimerService;

public class ValidateChoice extends GameAction {

    private final TimerService timerService;
    private final int guessingNumber;
    private int attempts = 0;

    public ValidateChoice( TimerService timerService, PlayerInfoEntity playerInfo) {
        super( playerInfo );
        this.timerService = timerService;
        this.guessingNumber = this.getGuessingNumber();
        System.out.println(guessingNumber);
    }

    @Override
    public void execute() {
        final boolean guessed = this.isGuessed();
        final int chances = playerInfo.getDifficultyLevel().getChances();

        if( guessed && attempts <= chances ) {
            final long time = this.timerService.endTimer();
            System.out.printf("\nYou took %s secs to guess the number\n", time);
            return;
        }

        if( !guessed && attempts < chances ) {
            this.executeNext();
            return;
        }

        System.out.println("\nYou runned out of chances...");
        System.out.printf("The number is %s", guessingNumber);
        System.out.println("\nGAME OVER!\n");
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
