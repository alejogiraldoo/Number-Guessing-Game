package com.alejogiraldoo.infraestructure.services;

public class ClueService {

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

    private static int getCloseRandomNumber(int min, int max ) {
        final int range = ( max - min ) + 1;
        return (int) ( ( range * Math.random() ) + min );
    }

    private static void showCloseNumbers( int guessingNumber, int range ) {
        int leftCloseNumber = getCloseRandomNumber(guessingNumber - range, ++guessingNumber);
        int rightCloseNumber = getCloseRandomNumber(guessingNumber + range, ++guessingNumber);

        System.out.printf("CLUE: The number is between %s and %s\n", leftCloseNumber, rightCloseNumber);
    }
}
