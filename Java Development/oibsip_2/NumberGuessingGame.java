import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxNumber = 100; // Maximum number for the game
        int round = 1; // The current round
        int score = 0; // The total score
        boolean playAgain = true; // Whether the user wants to play again

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            System.out.println("\nRound " + round + ":");
            int randomNumber = random.nextInt(maxNumber) + 1; // Generate a random number
            int attempts = 0; // The number of attempts for this round

            while (true) {
                System.out.print("Guess a number between 1 and " + maxNumber + ": ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + "th attempts.");
                    score += ( (11 - attempts) * 10); // Calculate the score
                    break;
                } else if (guess > randomNumber){
                    if((guess - randomNumber)>10) {
                        System.out.println("Too high. Try again.");
                    }
                    else {
                        System.out.println("Little high. Try again.");
                    }
                } else if((randomNumber - guess)>10){
                    System.out.println("Too low. Try again.");
                }
                else {
                    System.out.println("Little low.Try again.");
                }

                if (attempts == 10) {
                    System.out.println("Sorry, you ran out of attempts. The number was " + randomNumber + ".");
                    break;
                }
            }

            System.out.println("Your current score is " + score);

            System.out.print("\nDo you want to play again? (yes or no): ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("no")) {
                playAgain = false;
            } else {
                round++;
            }
        }

        System.out.println("\nGame over. Your final score is " + score);
    }
}
