package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.entities.PlayerInfo;
import com.alejogiraldoo.infraestructure.services.ClueService;
import com.alejogiraldoo.infraestructure.services.TimerService;
import com.alejogiraldoo.infraestructure.utils.RandomNumber;

public class ValidateChoice extends GameAction {

    private final TimerService timerService;
    private final ClueService.ClueProvider clueProvider = ClueService::showClue;
    private final RandomNumber.RandomNumberProvider randomNumberProvider = RandomNumber::get;

    private final int guessingNumber;
    private int attempts = 0;

    public ValidateChoice(TimerService timerService, PlayerInfo playerInfo) {
        super(playerInfo);
        this.timerService = timerService;

        PlayerInfo.Settings settings = playerInfo.getSettings();
        this.guessingNumber = this.randomNumberProvider.get(settings.getStartingNumber(), settings.getEndingNumber());

    }

    @Override
    public void execute() {
        final boolean guessed = this.isGuessed();
        final int chances = playerInfo.getDifficultyLevel().getChances();

        if (guessed && attempts <= chances) {
            final long time = this.timerService.endTimer();
            System.out.printf("\nYou took %s secs to guess the number\n", time);
            return;
        }

        if (!guessed && attempts < chances) {
            int leftChances = chances - attempts;
            this.clueProvider.showClue(new ClueService.GameState(leftChances, guessingNumber));
            System.out.printf("You have %s attempts left.\n", leftChances);
            this.executeNext();
            return;
        }

        System.out.println("\nYou runned out of chances...");
        System.out.printf("The number is %s", guessingNumber);
        System.out.println("\nGAME OVER!\n");
    }

    private boolean isGuessed() {
        final int choice = playerInfo.getChoice();
        attempts++;

        if (Integer.compare(choice, guessingNumber) == 0) {
            System.out.printf("Congratulations! You guessed the correct number in %s attempts\n", attempts);
            return true;
        }

        if (choice < guessingNumber) {
            System.out.printf("Incorrect! The number is greater than %s.\n", choice);
            return false;
        }

        System.out.printf("Incorrect! The number is less than %s.\n", choice);
        return false;
    }
}
