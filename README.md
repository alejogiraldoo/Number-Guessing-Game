# Number Guessing Game CLI

A lightweight, interactive Command Line Interface (CLI) application built in Java where players attempt to guess a
randomly generated number between 1 and 100 within a limited number of chances based on the selected difficulty.

This project is a solution to the [Number Guessing Game](https://roadmap.sh/projects/number-guessing-game) challenge
from **Roadmap.sh**.

## Features

- **Dynamic Difficulty Levels**:
    - **Easy**: 10 chances
    - **Medium**: 5 chances
    - **Hard**: 3 chances
- **Real-Time Feedback**: Clear indicators telling the player whether the secret number is higher or lower than their
  current guess.
- **Replayability**: Play multiple consecutive rounds without having to restart the application.
- **Timer System**: Tracks and displays the exact time taken to successfully guess the number.
- **Hint System**: Provides smart clues to guide players when they get stuck.

## Technical Details

- **Language:** Java (JDK 17 or higher recommended).
- **Build Tool**: Apache Maven.
- **Testing**: JUnit 5 & Mockito.
- **Patterns used:** Chain of Responsibility

## Installation & How to Run

This project uses **Apache Maven** for dependency management and testing execution (JUnit 5 & Mockito). **You do not
need Maven installed globally on your machine** to run or test this project.

Choose the method that best fits your environment:

#### Clone this repository:

   ```bash
   git clone https://github.com/alejogiraldoo/Number-Guessing-Game.git
  ```

### Option 1: Using IntelliJ IDEA (Recommended - Bundled Maven)

If you are using IntelliJ IDEA, you don't need to install Maven on your operating system:

1. Open IntelliJ IDEA ➔ Select Open ➔ Choose the project folder.

2. IntelliJ will automatically detect the pom.xml file and download all dependencies using its bundled Maven engine.

3. Open src/main/java/Main.java and click the Run ▶ button (or press Shift + F10).

4. To run tests, right-click the src/test/java directory and select Run 'All Tests'.

#### Option 2: Using Command Line / Terminal

If you have Maven installed globally:

  ```bash
    mvn compile exec:java           # Run the application
  ```

## Sample Gameplay

```text
Welcome to the Number Guessing Game!
I'm thinking of a number between 1 and 100.
You have 5 chances to guess the correct number.

Please select the difficulty level:
1. Easy (10 chances)
2. Medium (5 chances)
3. Hard (3 chances)

Enter your choice: 2

Great! You have selected the Medium difficulty level.
Let's start the game!

Enter your guess: 50
Incorrect! The number is less than 50.

Enter your guess: 25
Incorrect! The number is greater than 25.

Enter your guess: 35
Incorrect! The number is less than 35.

Enter your guess: 30
Congratulations! You guessed the correct number in 4 attempts.
```

## Study References

- [Java Enum Constructor](https://www.w3schools.com/java/java_enum_constructor.asp)
- [Chain of Responsibility](https://refactoring.guru/design-patterns/chain-of-responsibility)
- [What is Math random () Method in Java?](https://codegym.cc/groups/posts/java-mathrandom-method)
- [Run a Maven project without installing Maven](https://coderanch.com/t/775207/maven/build-tools/Run-Maven-project-installing-Maven)
- [Where is bundled maven folder in IntelliJ?](https://stackoverflow.com/questions/71950579/where-is-bundled-maven-folder-in-intellij)