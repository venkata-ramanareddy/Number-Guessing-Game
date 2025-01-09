import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize variables
        int lowerBound = 1; // lower bound for the random number
        int upperBound = 100; // upper bound for the random number
        int maxAttempts = 10; // maximum attempts allowed per round
        int totalRounds = 3; // total rounds in the game
        int totalScore = 0; // total score across all rounds

        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Start the game with multiple rounds
        for (int round = 1; round <= totalRounds; round++) {
            int attemptsLeft = maxAttempts; // attempts left for this round
            int roundScore = 0; // score for the current round
            int targetNumber = generateRandomNumber(lowerBound, upperBound); // generated number
            boolean isCorrectGuess = false; // flag to track if the user guessed correctly

            System.out.println("Round " + round + " of " + totalRounds);
            System.out.println("Guess the number between " + lowerBound + " and " + upperBound);
            System.out.println("You have " + attemptsLeft + " attempts to guess the number.");

            // User guesses the number within the allowed attempts
            while (attemptsLeft > 0 && !isCorrectGuess) {
                System.out.print("Enter your guess: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume invalid input
                }
                int userGuess = scanner.nextInt();

                if (userGuess < lowerBound || userGuess > upperBound) {
                    System.out.println("Please enter a number between " + lowerBound + " and " + upperBound);
                    continue; // Skip the rest of the loop if the guess is out of bounds
                }

                attemptsLeft--; // Decrease the attempts left

                if (userGuess == targetNumber) {
                    isCorrectGuess = true;
                    roundScore = attemptsLeft + 1; // Points based on remaining attempts
                    System.out.println("Congratulations! You guessed the number correctly.");
                } else if (userGuess < targetNumber) {
                    System.out.println("The number is higher. Try again.");
                } else {
                    System.out.println("The number is lower. Try again.");
                }

                // Display remaining attempts
                if (attemptsLeft > 0 && !isCorrectGuess) {
                    System.out.println("You have " + attemptsLeft + " attempts left.");
                }
            }

            // End of round - handle no correct guess
            if (!isCorrectGuess) {
                System.out.println("Sorry! You've run out of attempts. The correct number was: " + targetNumber);
                roundScore = 0; // No points if user didn't guess correctly
            }

            // Update total score
            totalScore += roundScore;
            System.out.println("Your score for this round is: " + roundScore);
            System.out.println("Total score so far: " + totalScore);
            System.out.println("-------------------------------------------");
        }

        // End of game - Display total score
        System.out.println("Game Over! Your total score is: " + totalScore);
        scanner.close(); // Close the scanner resource
    }

    // Method to generate a random number between a specified range
    public static int generateRandomNumber(int lower, int upper) {
        Random random = new Random();
        return random.nextInt(upper - lower + 1) + lower; // Random number between lower and upper (inclusive)
    }
}
