import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ZahlenRatenVersion1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String isBigger = "Großer! Die geheime Zahl ist größer.\n";
        String isSmaller = "Kleiner! Die geheime Zahl ist kleiner.\n";
        String isCorrect = "Erraten! Deine Antwort ist korrekt.\n";


        int levelChoice = 100;

        while (levelChoice != 0) {

            System.out.println("\n***** ZahlenRatenSpiel *****");
            System.out.println("""
                    Wähle, welches Level du spielen möchtest. Gib eine Zahl zwischen 1 und 3 ein, 0 0 zum Beenden des Spiels.
                                        
                    0. Spiel Beenden
                    1. Level One
                    2. Level Two
                    3. Level Three
                    """);

            System.out.println("Geben Sie Ihre Auswahl ein. z.B. (1 für Level 1): ");
            levelChoice = sc.nextInt();

            switch (levelChoice) {

                case 1:

                    boolean stillPlaying = true;
                    int numberOfTries = 9; //total number of tries

                    while (stillPlaying && numberOfTries > 0) { //to reloop if the player decides to play again

                        boolean gameOn = true;
                        int secretNumber = 10;
                        int userGuess = 20;

                        System.out.println("***** ZahlenRatenSpiel Level 1 *****\n");
                        secretNumber = rand.nextInt(101);

                        while (gameOn) {

                            System.out.println(secretNumber);

                            if (numberOfTries != 0) {

                                do {
                                    System.out.println("\nErrate die versteckte Zahl. (1-100) ");
                                    userGuess = sc.nextInt();

                                    if (userGuess < 0 || userGuess > 100) {
                                        System.out.println("\nUngültige Zahl. Zahl von 1 bis 100 eingeben:");
                                    }

                                } while (userGuess < 0 || userGuess > 100);


                                if (secretNumber == userGuess) {
                                    System.out.println("You guessed correctly!");


                                    System.out.println("Do you want to play again [y][n]");
                                    char playAgain = sc.next().charAt(0);

                                    if (playAgain == 'n') {
                                        System.out.println("Thank you for playing!");
                                        stillPlaying = false;
                                        gameOn = false;
                                    } else if (playAgain == 'y') {
                                        gameOn = false;
                                    }

                                } else if (secretNumber != userGuess) {

                                    if (secretNumber > userGuess) {
                                        System.out.println(isBigger);
                                        numberOfTries--;

                                    } else if (secretNumber < userGuess) {
                                        System.out.println(isSmaller);
                                        numberOfTries--;
                                    }

                                    if (numberOfTries > 0) {
                                        System.out.println("Your remaining chance to guess: " + numberOfTries);
                                    } else {
                                        System.out.println("You already exhausted Your chance, See you next time.");
                                    }
                                }

                            } else {
                                gameOn = false;
                            }
                        }
                    }

                    break;

                case 2:

                    boolean gameIsActive = true;
                    int numberOfAttempts = 9; //total number of tries

                    ArrayList<Integer> listOfGuessedNumbers = new ArrayList<>();

                    while (gameIsActive && numberOfAttempts > 0) { //to reloop if the player decides to play again

                        boolean stillOnTheGame = true;
                        int numberToGuess = 10;
                        int playerGuess = 20;

                        System.out.println("***** ZahlenRatenSpiel Level 2 *****\n");
                        numberToGuess = rand.nextInt(101);

                        while (stillOnTheGame) {

                            System.out.println(numberToGuess);

                            if (numberOfAttempts != 0) {

                                do {
                                    System.out.println("\nErrate die versteckte Zahl. (1-100) ");
                                    playerGuess = sc.nextInt();

                                    if (playerGuess < 0 || playerGuess > 100) {
                                        System.out.println("\nUngültige Zahl. Zahl von 1 bis 100 eingeben:");
                                    }

                                } while (playerGuess < 0 || playerGuess > 100);


                                if (numberToGuess == playerGuess) {
                                    System.out.println("You guessed correctly!");


                                    System.out.println("Do you want to play again [y][n]");
                                    char playAgain = sc.next().charAt(0);

                                    if (playAgain == 'n') {
                                        System.out.println("Thank you for playing!");
                                        gameIsActive = false;
                                        stillOnTheGame = false;
                                    } else if (playAgain == 'y') {
                                        stillOnTheGame = false;
                                    }

                                } else  {
                                    int diff = Math.abs(playerGuess - numberToGuess); //ensure value is positive

                                    if ( diff <= 3 ) {
                                        System.out.println("fast da: zwischen 1 und 3 daneben.");
                                    } else if (diff <= 10 ) {
                                        System.out.println("relative nahe: : zwischen 4 und 10 daneben");
                                    } else if (diff <= 20 ) {
                                        System.out.println("Nicht ganz so weit weg: zwischen 11 und 20 daneben");
                                    } else {
                                        System.out.println("weit weg, > daneben");
                                    }


                                    numberOfAttempts--;

                                    if (numberOfAttempts > 0) {
                                        System.out.println("Your remaining chance to guess: " + numberOfAttempts);
                                    } else {
                                        System.out.println("You already exhausted Your chance, See you next time.");
                                    }
                                }

                            } else {
                                stillOnTheGame = false;
                            }
                        }
                    }


                    break;


                case 3:

                    System.out.println("\n***** ZahlenRatenSpiel Level 3 *****\n");

                    //int numberToGuess = rand.nextInt(101);
                    boolean playerTurn = rand.nextBoolean(); // to know who will start

                    int numberToGuess = rand.nextInt(101);

                    boolean guessed = false;

                    int lowerBound = 0; // the lower Side to compute the median
                    int higherBound = 101; //the Upper Side +1 So that KI can still guess if random is 100.
                    int userGuess;
                    int kiGuess;

                    //System.out.println(numberToGuess);

//                    String feedbackHigher = "The number to guess is higher than your guess";
//                    String feedbackLower = "The number to guess is lower than your guess";

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

                                System.out.println(isBigger);

                            } else if (numberToGuess < userGuess) {
                                if (userGuess < higherBound) {
                                    higherBound = userGuess - 1;
                                }
                                System.out.println(isSmaller);
                            }

                            listOfGuesses.add(userGuess);

                        } else {

                            kiGuess = (lowerBound + higherBound) / 2;
                            listOfGuesses.add(kiGuess);
                            System.out.println("It's KI turn: " + kiGuess);

                            if (numberToGuess > kiGuess) {
                                lowerBound = kiGuess + 1;
                                System.out.println(isBigger);

                            } else if (numberToGuess < kiGuess) {
                                higherBound = kiGuess - 1;
                                System.out.println(isSmaller);

                            } else if (numberToGuess == kiGuess) {
                                System.out.println("KI, " + isCorrect);
                                listOfGuesses.add(kiGuess);
                                guessed = true;
                            }
                        }

                        playerTurn = !playerTurn;
                    }

                    break;

                default:
            }

        }

    }
}
