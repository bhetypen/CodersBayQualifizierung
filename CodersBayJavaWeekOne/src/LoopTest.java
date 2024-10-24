import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LoopTest {
    public static void main(String[] args) {

        ArrayList<Integer> listOfNumbers = new ArrayList<>(); ;

        for (int i = 0; i < 100; i++) {
            listOfNumbers.add(i);
        }

        System.out.println(listOfNumbers);

        Random rand = new Random();

        int newNumberToGuess = 0;

        for (int i = 0; i < 100; i++) {
            newNumberToGuess = rand.nextInt(101);
        }

        System.out.println("Number to Guess: " + newNumberToGuess);

        int feedback = 0; //1 for großer 2 for kleiner 3 for erraten

        int computerGuess = 50;

        System.out.println(computerGuess);

        int increment1 = 12;
        int increment2 = 6;
        int increment3 = 3;
        int increment4 = 2;

        boolean gameOver = true;

        while (!gameOver) {

            if(computerGuess < newNumberToGuess) {
                if (computerGuess == 50) {
                    computerGuess = computerGuess + (computerGuess/2);
                } else
                if (computerGuess == 75 || computerGuess == 25) {
                    computerGuess = computerGuess + increment1;
                } else

                if (computerGuess == 87 || computerGuess == 37) {
                    computerGuess = computerGuess + increment2; ;
                }
                else
                if (computerGuess == 93 || computerGuess == 43) {
                    computerGuess = computerGuess + increment3;
                }
                else
                if (computerGuess == 96 || computerGuess == 46) {
                    computerGuess = computerGuess + increment4;
                }
                else
                if (computerGuess >= 98 || (computerGuess >= 48 && computerGuess < 50) ) {
                    computerGuess = computerGuess + 1;
                }

                System.out.println(computerGuess);

                //feedback = 1;

            } else if (computerGuess > newNumberToGuess) {

                    if (computerGuess == 50) {
                        computerGuess = computerGuess - (computerGuess/2);
                    }
                    if (computerGuess == 25 || computerGuess == 75) {
                        computerGuess = computerGuess - increment1;
                    }

                    if (computerGuess == 63 || computerGuess == 13) {
                        computerGuess = computerGuess - increment2; ;
                    }

                    if (computerGuess == 57 || computerGuess == 6) {
                        computerGuess = computerGuess - increment3;
                    }

                    if (computerGuess == 3 || computerGuess == 54) {
                        computerGuess = computerGuess - increment4;
                    }

                    if (computerGuess == 2 || computerGuess == 52) {
                        computerGuess = computerGuess - 1;
                    }

                    System.out.println(computerGuess);

                //feedback = 2;
            } else {
                System.out.println("You guess it");
                gameOver = false;
            }

//            System.out.println(computerGuess);
//            switch (feedback) {
//                case 1:
//                    System.out.println("Die Geheimzahl ist Großer");
//                case 2:
//                    System.out.println("Die Geheimzahl ist Kleiner");
//                default:
//                    if (computerGuess == newNumberToGuess) {
//                        System.out.println("Your guess is right");
//                    }
//                    gameOver = true;
//            }


        }






    }
}
