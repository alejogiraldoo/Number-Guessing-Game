package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.enums.EResultType;
import com.alejogiraldoo.infraestructure.services.ClueService;
import com.alejogiraldoo.infraestructure.services.TimerService;
import com.alejogiraldoo.infraestructure.utils.RandomNumber;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;

import java.time.LocalTime;
import java.util.Objects;

public class ValidateChoice extends GameAction {

    private final GetChoice.ChoiceGetter choiceGetter;
    private final TimerService timerService;
    private final ClueService.ClueProvider clueProvider = ClueService::showClue;
    private final RandomNumber.RandomNumberProvider randomNumberProvider = RandomNumber::get;

    private final int guessingNumber;
    private int attempts = 0;

    public ValidateChoice(
            GetChoice.ChoiceGetter choiceGetter,
            TimerService timerService,
            RoundInfo roundInfo
    ) {
        super(roundInfo);
        this.choiceGetter = choiceGetter;
        this.timerService = timerService;

        RoundInfo.Settings settings = roundInfo.getSettings();
        this.guessingNumber = this.randomNumberProvider.get(settings.startingNumber(), settings.endingNumber());
        this.roundInfo.setGuessingNumber(guessingNumber);
    }

    @Override
    public void execute() {
        final boolean guessed = this.isGuessed();
        final int chances = roundInfo.getDifficulty().getChances();

        if (guessed && attempts <= chances) {
            this.setRoundInfo(EResultType.Win);
            return;
        }

        if (!guessed && attempts < chances) {
            int leftChances = chances - attempts;
            this.clueProvider.showClue(new ClueService.GameState(leftChances, guessingNumber));
            System.out.printf("You have %s attempts left.\n", leftChances);
            this.choiceGetter.getChoice();
            return;
        }

        this.setRoundInfo(EResultType.Loss);
    }

    private boolean isGuessed() {
        final int choice = roundInfo.getChoice();
        attempts++;

        if (Objects.equals(choice, guessingNumber)) {
            System.out.printf("Congratulations! You guessed the correct number in %s attempts.\n", attempts);
            return true;
        }

        if (choice < guessingNumber) {
            System.out.printf("Incorrect! The number is greater than %s.\n", choice);
            return false;
        }

        System.out.printf("Incorrect! The number is less than %s.\n", choice);
        return false;
    }

    private void setRoundInfo(EResultType gameResult) {
        final LocalTime time = this.timerService.endTimer();
        roundInfo.setTakenTime(time);
        roundInfo.setGameResult(gameResult);
        roundInfo.setAttempts(attempts);
        this.executeNext();
    }
}
