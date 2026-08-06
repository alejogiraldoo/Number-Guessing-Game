# Number Guessing Game CLI

A lightweight, interactive Command Line Interface (CLI) application built in Java where players attempt to guess a randomly generated number between 1 and 100 within a limited number of chances based on the selected difficulty.

This project is a solution to the [Number Guessing Game](https://roadmap.sh/projects/number-guessing-game) challenge from **Roadmap.sh**.

## Features

- **Dynamic Difficulty Levels**:
    - **Easy**: 10 chances
    - **Medium**: 5 chances
    - **Hard**: 3 chances
- **Real-Time Feedback**: Clear indicators telling the player whether the secret number is higher or lower than their current guess.
- **Replayability**: Play multiple consecutive rounds without having to restart the application.
- **Timer System**: Tracks and displays the exact time taken to successfully guess the number.
- **Hint System**: Provides smart clues to guide players when they get stuck.

## Technical Details

- Language: Java
- Patterns used: Chain of Responsibility

### Study References
- [Java Enum Constructor]("https://www.w3schools.com/java/java_enum_constructor.asp")
- [Chain of Responsibility]("https://refactoring.guru/design-patterns/chain-of-responsibility")
- [What is Math random() Method in Java?]("https://codegym.cc/groups/posts/java-mathrandom-method")