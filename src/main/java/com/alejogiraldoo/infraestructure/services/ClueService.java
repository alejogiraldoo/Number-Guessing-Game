package com.alejogiraldoo.infraestructure.services;

import com.alejogiraldoo.infraestructure.utils.RandomNumber;

public class ClueService {

    private static RandomNumber.RandomNumberProvider randomNumberProvider = RandomNumber::get;

    @FunctionalInterface
    public interface ClueProvider {
        void showClue( GameState state );
    }

    public record GameState(
            int leftChances,
            int guessingNumber
    ) {}

    public static void showClue( GameState state ) {

        if( state.leftChances == 7 ) {
            showCloseNumbers( state.guessingNumber(), 30 );
            return;
        }

        if( state.leftChances == 5 ) {
            showCloseNumbers( state.guessingNumber(), 10 );
            return;
        }

        if( state.leftChances ==  2 ) {
            showCloseNumbers( state.guessingNumber(), 5 );
            return;
        }
    }


    private static void showCloseNumbers( int guessingNumber, int range ) {
        int leftCloseNumber = randomNumberProvider.get(guessingNumber - range, guessingNumber - 1);
        int rightCloseNumber = randomNumberProvider.get(guessingNumber + 1, guessingNumber + range);

        System.out.printf("CLUE: The number is between %s and %s\n", leftCloseNumber, rightCloseNumber);
    }
}
