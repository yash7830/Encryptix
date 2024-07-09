import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        String playAgain;

        do {
            int numberToGuess = random.nextInt(100) + 1;
            int attemptsLeft = 10;
            int score = 0;

            System.out.println("\nStarting a new round...");
            
            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (between 1 and 100): ");
                int userGuess = scanner.nextInt();
                
                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Please enter a number within the range.");
                    continue;
                }

                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score = attemptsLeft + 1; // +1 because we decrement attempts before checking the guess
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }
            }

            if (score == 0) {
                System.out.println("Sorry, you're out of attempts. The correct number was " + numberToGuess + ".");
            }

            totalScore += score;
            System.out.println("Your score for this round: " + score);

            System.out.print("Do you want to play again? (y/n): ");
            playAgain = scanner.next();
        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("\nFinal Score: " + totalScore);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}