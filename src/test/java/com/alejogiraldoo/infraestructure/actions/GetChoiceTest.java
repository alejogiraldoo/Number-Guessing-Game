package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.infraestructure.services.TimerService;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetChoiceTest {

    private final int choice = 5;

    @Mock
    private Scanner scMock;

    @Mock
    private TimerService timerServiceMock;

    @Mock
    private RoundInfo.Settings settingsMock;

    @Mock
    private RoundInfo RoundInfoMock;

    @Spy
    private final PrintStream systemOutSpy = System.out;

    @InjectMocks
    @Spy
    private GetChoice getChoice;

    @BeforeEach
    void init() {
        doReturn(1).when(settingsMock).getStartingNumber();
        doReturn(100).when(settingsMock).getEndingNumber();
        doReturn(settingsMock).when(RoundInfoMock).getSettings();

        doAnswer((in) -> null).when(systemOutSpy).print(anyString());

        System.setOut(systemOutSpy);
    }

    @Test
    @DisplayName("execute() should start Timer if it wasn't started yet")
    void shouldStartTimer() {
        doReturn(false).when(timerServiceMock).isTimerActive();
        doReturn(choice).when(scMock).nextInt();
        doNothing().when(getChoice).executeNext();

        getChoice.execute();
        verify(timerServiceMock).startTimer();
    }

    @Test
    @DisplayName("execute() shouldn't start Timer if it was already started")
    void shouldNotStartTimer() {
        doReturn(true).when(timerServiceMock).isTimerActive();
        doReturn(choice).when(scMock).nextInt();
        doNothing().when(getChoice).executeNext();

        getChoice.execute();
        verify(timerServiceMock, never()).startTimer();
    }

    @Test
    @DisplayName("execute() should get and set the player choice")
    void shouldSetAndGetChoice() {
        doNothing().when(getChoice).executeNext();
        doReturn(choice).when(scMock).nextInt();

        getChoice.execute();

        verify(systemOutSpy).print("\nEnter your guess: ");
        verify(scMock).nextInt();
        verify(RoundInfoMock).setChoice(choice);
        verify(getChoice).executeNext();
    }

    @Test
    @DisplayName("execute() should print an error when input is not a number")
    void shouldThrowErrorWhenInputIsNotANumber() {

        doThrow(new InputMismatchException())
                .doReturn(choice)
                .when(scMock).nextInt();

        doAnswer((in) -> null).when(systemOutSpy).printf(anyString(), anyInt(), anyInt());
        doNothing().when(getChoice).executeNext();

        getChoice.execute();

        verify(systemOutSpy).printf("Please insert a number between %s and %s \n", settingsMock.getStartingNumber(), settingsMock.getEndingNumber());
        verify(scMock, atLeastOnce()).nextInt();
        verify(RoundInfoMock, atMostOnce()).setChoice(anyInt());
        verify(getChoice, atMostOnce()).executeNext();
    }

    @Test
    @DisplayName("execute() should print an error when input is out of range")
    void shouldThrowErrorWhenInputIsOutOfRange() {

        doReturn(200)
                .doReturn(50)
                .when(scMock).nextInt();

        doAnswer((in) -> null).when(systemOutSpy).printf(anyString(), anyInt(), anyInt());
        doNothing().when(getChoice).executeNext();

        getChoice.execute();

        verify(systemOutSpy).printf("Please insert a number between %s and %s \n", settingsMock.getStartingNumber(), settingsMock.getEndingNumber());
        verify(scMock, atLeastOnce()).nextInt();
        verify(RoundInfoMock, atMostOnce()).setChoice(anyInt());
        verify(getChoice, atMostOnce()).executeNext();
    }

    @AfterEach
    void release() {
        System.setOut(System.out);
    }

}