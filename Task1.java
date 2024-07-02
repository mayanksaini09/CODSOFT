import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static int generateNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static int getUserGuess(Scanner scanner) {
        System.out.print("Enter your guess: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            scanner.next(); // Clear the invalid input
            System.out.print("Enter your guess: ");
        }
        return scanner.nextInt();
    }

    public static void giveFeedback(int guess, int number) {
        if (guess < number) {
            System.out.println("Your guess is too low.");
        } else if (guess > number) {
            System.out.println("Your guess is too high.");
        } else {
            System.out.println("Congratulations! You guessed the correct number.");
        }
    }

    public static int playGame(int minRange, int maxRange, int maxAttempts, Scanner scanner) {
        int number = generateNumber(minRange, maxRange);
        int attempts = 0;

        while (attempts < maxAttempts) {
            int guess = getUserGuess(scanner);
            attempts++;
            giveFeedback(guess, number);

            if (guess == number) {
                return attempts;
            }
        }
        System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + number + ".");
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalRounds = 0;
        int totalAttempts = 0;
        int roundsWon = 0;

        while (true) {
            System.out.println("\nNew Round!");
            int attempts = playGame(1, 100, 10, scanner);

            totalRounds++;
            if (attempts != -1) {
                totalAttempts += attempts;
                roundsWon++;
            }

            System.out.println("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total rounds won: " + roundsWon);
        if (roundsWon > 0) {
            System.out.println("Average attempts per win: " + (double) totalAttempts / roundsWon);
        }

        scanner.close();
    }
}
