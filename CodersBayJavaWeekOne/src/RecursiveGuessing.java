import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RecursiveGuessing {
    public static void main(String[] args) {

        Random rand = new Random();
        int numberToGuess = rand.nextInt(101);
        System.out.println(numberToGuess);

        int computerGuess = 50;
        int arraySize = 50;

        System.out.println("firtst Guess: "+computerGuess);

        boolean guessed = false;
        int feedback = 0;

        ArrayList<Integer> guesses = new ArrayList<Integer>();

        int firstGuess = guesses.getFirst();

        while (!guessed) {

            if (numberToGuess > computerGuess) {
                feedback = 1;
            } else if (numberToGuess < computerGuess) {
                feedback = 2;
            } else if (numberToGuess == computerGuess) {

                guessed = true;
            }


            switch (feedback) {
                case 1:

                        System.out.println("großer");

                            computerGuess = computerGuess + (arraySize/2);
                            arraySize = arraySize/2;
                            guesses.add(computerGuess);
                            break;



                case 2:

                        System.out.println("kleiner");
                        computerGuess = computerGuess - (arraySize/2);
                        arraySize = arraySize/2;
                        guesses.add(computerGuess);
                        break;

                default:

                    System.out.println("You guessed it");


            }

            System.out.println("More Guesses: "+computerGuess);
            System.out.println(guesses);


        }








    }
}
