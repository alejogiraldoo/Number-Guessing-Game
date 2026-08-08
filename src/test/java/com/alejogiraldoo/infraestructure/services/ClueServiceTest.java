package com.alejogiraldoo.infraestructure.services;

import com.alejogiraldoo.infraestructure.utils.RandomNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClueServiceTest {

    @Captor
    private ArgumentCaptor<String> clueTextArg;

    @Captor
    private ArgumentCaptor<Integer> closeNumberArg;

    @Spy
    private final PrintStream systemOutSpy = System.out;

    private final int guessingNumber = 10;

    @BeforeEach
    void init() {
        doAnswer((in) -> null).when(systemOutSpy).printf(anyString(), anyInt(), anyInt());
        System.setOut(systemOutSpy);
    }

    @Test
    @DisplayName("showClue() should show a clue to the player when there is 7 chances left")
    void shouldShowClueWhen7ChancesLeft() {
        try (MockedStatic<RandomNumber> randomNumberMock = mockStatic(RandomNumber.class)) {
            final int leftChances = 7;
            final int range = 30;
            final ClueService.GameState gameState = new ClueService.GameState(leftChances, guessingNumber);

            randomNumberMock.when(() -> RandomNumber.get(anyInt(), anyInt())).thenAnswer(InvocationOnMock::callRealMethod);
            ClueService.showClue(gameState);

            InOrder inOrder = inOrder(RandomNumber.class);
            inOrder.verify(
                    randomNumberMock,
                    () -> RandomNumber.get(guessingNumber - range, guessingNumber - 1)
            );

            inOrder.verify(
                    randomNumberMock,
                    () -> RandomNumber.get(guessingNumber + 1, guessingNumber + range)
            );

            verify(systemOutSpy).printf(clueTextArg.capture(), closeNumberArg.capture(), closeNumberArg.capture());

            assertAll(
                    () -> assertEquals("CLUE: The number is between %s and %s\n", clueTextArg.getValue()),
                    () -> assertTrue(closeNumberArg.getAllValues().getFirst() < guessingNumber),
                    () -> assertTrue(closeNumberArg.getAllValues().getLast() > guessingNumber)
            );
        }

    }

    @Test
    @DisplayName("showClue() should show a clue to the player when there is 5 chances left")
    void shouldShowClueWhen5ChancesLeft() {
        try (MockedStatic<RandomNumber> randomNumberMock = mockStatic(RandomNumber.class)) {
            final int leftChances = 5;
            final int range = 10;
            final ClueService.GameState gameState = new ClueService.GameState(leftChances, guessingNumber);

            randomNumberMock.when(() -> RandomNumber.get(anyInt(), anyInt())).thenAnswer(InvocationOnMock::callRealMethod);
            ClueService.showClue(gameState);

            InOrder inOrder = inOrder(RandomNumber.class);
            inOrder.verify(
                    randomNumberMock,
                    () -> RandomNumber.get(guessingNumber - range, guessingNumber - 1)
            );

            inOrder.verify(
                    randomNumberMock,
                    () -> RandomNumber.get(guessingNumber + 1, guessingNumber + range)
            );

            verify(systemOutSpy).printf(clueTextArg.capture(), closeNumberArg.capture(), closeNumberArg.capture());
            assertAll(
                    () -> assertEquals("CLUE: The number is between %s and %s\n", clueTextArg.getValue()),
                    () -> assertTrue(closeNumberArg.getAllValues().getFirst() < guessingNumber),
                    () -> assertTrue(closeNumberArg.getAllValues().getLast() > guessingNumber)
            );
        }
    }

    @Test
    @DisplayName("showClue() should show a clue to the player when there is 2 chances left")
    void shouldShowClueWhen2ChancesLeft() {
        try (MockedStatic<RandomNumber> randomNumberMock = mockStatic(RandomNumber.class)) {
            final int leftChances = 2;
            final int range = 5;
            final ClueService.GameState gameState = new ClueService.GameState(leftChances, guessingNumber);

            randomNumberMock.when(() -> RandomNumber.get(anyInt(), anyInt())).thenAnswer(InvocationOnMock::callRealMethod);
            ClueService.showClue(gameState);

            InOrder inOrder = inOrder(RandomNumber.class);
            inOrder.verify(
                    randomNumberMock,
                    () -> RandomNumber.get(guessingNumber - range, guessingNumber - 1)
            );

            inOrder.verify(
                    randomNumberMock,
                    () -> RandomNumber.get(guessingNumber + 1, guessingNumber + range)
            );

            verify(systemOutSpy).printf(clueTextArg.capture(), closeNumberArg.capture(), closeNumberArg.capture());
            assertAll(
                    () -> assertEquals("CLUE: The number is between %s and %s\n", clueTextArg.getValue()),
                    () -> assertTrue(closeNumberArg.getAllValues().getFirst() < guessingNumber),
                    () -> assertTrue(closeNumberArg.getAllValues().getLast() > guessingNumber)
            );
        }
    }

    @AfterEach
    void release() {
        System.setOut(System.out);
    }

}
