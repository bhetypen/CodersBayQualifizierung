import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ZahlenRatenLevel3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        //int numberToGuess = rand.nextInt(101);
        boolean playerTurn = rand.nextBoolean();

        int numberToGuess = 100;

        boolean guessed = false;

        int lowerBound = 0; // the lower Side to compute the median
        int higherBound = 101; //the Upper Side +1 So that KI can still guess if random is 100.
        int userGuess;
        int kiGuess;

        //System.out.println(numberToGuess);

        String feedbackHigher = "The number to guess is higher than your guess";
        String feedbackLower = "The number to guess is lower than your guess";

        ArrayList<Integer> listOfGuesses = new ArrayList<>();

        while (!guessed) {


            System.out.println(listOfGuesses);

            if (playerTurn) {
                System.out.println("Make your guess");
                userGuess = sc.nextInt();

                if (numberToGuess == userGuess) {
                    System.out.println("Player 1: Your guess was correct");
                    guessed = true;
                } else if (numberToGuess > userGuess) {
                    if (userGuess > lowerBound) {
                        lowerBound = userGuess + 1;
                    }

                    System.out.println(feedbackHigher);

                } else if (numberToGuess < userGuess) {
                    if (userGuess < higherBound) {
                        higherBound = userGuess - 1;
                    }
                    System.out.println(feedbackLower);

                }

                listOfGuesses.add(userGuess);

            } else {


                kiGuess = (lowerBound + higherBound) / 2;
                listOfGuesses.add(kiGuess);
                System.out.println("It's KI turn: " + kiGuess);


                if (numberToGuess > kiGuess) {
                    lowerBound = kiGuess + 1;
                    System.out.println(feedbackHigher);

                } else if (numberToGuess < kiGuess) {
                    higherBound = kiGuess - 1;
                    System.out.println(feedbackLower);

                } else if (numberToGuess == kiGuess) {
                    System.out.println("KI, Your guess was correct");
                    listOfGuesses.add(kiGuess);
                    guessed = true;
                }


            }

            playerTurn = !playerTurn;


        }


    }
}

