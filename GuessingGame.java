
import java.util.Random;
import java.util.Scanner;


    public class GuessingGame {

        // function to generate a random number between 1 and 100
        public static int numRandom() {
            Random generator = new Random();
            return generator.nextInt(100) + 1;
        }

        public static void main(String[] args) {

            Scanner keyboard = new Scanner(System.in);
            int reply;


            System.out.println("\n             !!! Welcome to the Guessing Game !!!");
            System.out.println("\n  !!! You get 5 chances to guess a number from 1 to 100 !!!");
            System.out.println("\n Would you like to play?");
            System.out.println("Enter 1 for Yes.");
            System.out.println("Enter 2 for No.");
            System.out.println("Enter here: ");

            reply = keyboard.nextInt();

            // Main game loop
            while (reply == 1) {
                int numRandom = numRandom();  // Generate a random number
                boolean guessedCorrectly = false;

                for (int i = 1; i <= 5; i++) {
                    System.out.print("\nEnter guess " + i + ": ");
                    int guess = keyboard.nextInt();

                    if (guess == numRandom) {
                        System.out.println("\n !!! You guessed it right !!!");
                        guessedCorrectly = true;
                        break;
                    }
                    else if(guess>numRandom) {
                        if (i != 5) {
                            System.out.println("Try smaller number...");
                            if(i==4)
                            {
                                System.out.println("You have only one chance now.");
                            }
                            else {
                                System.out.println("You have " + (5 - i) + " chances left");
                            }
                        }
                    }

                    else{
                        if(i!=5) {
                            System.out.println("Try Bigger number...");
                            if(i==4)
                            {
                                System.out.println("You have only one chance now.");
                            }
                            else {
                                System.out.println("You have " + (5 - i) + " chances left");
                            }
                        }
                    }
                }


                if (!guessedCorrectly) {
                    System.out.println("\n !!! Sorry, that is not correct !!!");
                    System.out.println("\n     The correct number was " + numRandom + ".");
                }

                System.out.println("\nWould you like to play again?");
                System.out.println(" 1. Yes");
                System.out.println(" 2. No");
                System.out.println("Enter here: ");
                reply = keyboard.nextInt();
            }

            // Display goodbye message when quitting
            System.out.println("\n     !!! Thanks for playing !!!");
            System.out.println("     !!! Goodbye !!!");

            keyboard.close();
        }
    }


