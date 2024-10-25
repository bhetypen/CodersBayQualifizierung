import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ZahlenRatenVersion1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        //so that i do not have to type several times :)
        String isBigger = "Großer! Die geheime Zahl ist größer.\n";
        String isSmaller = "Kleiner! Die geheime Zahl ist kleiner.\n";
        String isCorrect = "Erraten! Deine Antwort ist korrekt.\n";


        int levelChoice = -1;

        while (levelChoice != 0) {

            System.out.println("\n***** ZahlenRatenSpiel *****");
            System.out.println("""
                    Wähle, welches Level du spielen möchtest. Gib eine Zahl zwischen 1 und 3 ein, 0 zum Beenden des Spiels.
                    
                    0. Spiel Beenden
                    1. Level One
                    2. Level Two
                    3. Level Three
                    """);

            while (levelChoice < 0 || levelChoice > 3) {
                System.out.println("Geben Sie Ihre Auswahl ein. z.B. (1 für Level 1): ");
                levelChoice = sc.nextInt();

                if (levelChoice < 0 || levelChoice > 3) {
                    System.out.println("\nUngültige Eingabe. Bitte zwischen 0 und 3 eingeben\n");
                }
            }

            switch (levelChoice) {

                case 1:

                    boolean isLevelOneActive = true;
                    int numberOfTries = 9; //total number of tries

                    while (isLevelOneActive && numberOfTries > 0) { //to reloop if the player decides to play again

                        boolean isGameRoundOn = true;
                        int secretNumber;
                        int userGuess;

                        System.out.println("\n***** ZahlenRatenSpiel Level 1 *****\n");
                        secretNumber = rand.nextInt(101);

                        while (isGameRoundOn) {

                            System.out.println(secretNumber); //for debugging

                            do {
                                System.out.println("\nErrate die versteckte Zahl. (1-100) ");
                                userGuess = sc.nextInt();

                                if (userGuess < 0 || userGuess > 100) {
                                    System.out.println("\nUngültige Zahl. Zahl von 1 bis 100 eingeben:");
                                }

                            } while (userGuess < 0 || userGuess > 100);

                            numberOfTries--;

                            if (secretNumber == userGuess) {
                                System.out.println("Richtig geraten!\n");
                                numberOfTries = 9;

                                System.out.println("Möchten Sie noch einmal spielen? [y][n]");
                                char playAgain = sc.next().charAt(0);

                                if (playAgain == 'n') {
                                    System.out.println("Vielen Dank fürs Spielen!");
                                    isLevelOneActive = false;
                                    isGameRoundOn = false;
                                } else if (playAgain == 'y') {
                                    isGameRoundOn = false;
                                }

                            } else {

                                if (numberOfTries > 0) {

                                    if (secretNumber > userGuess) {
                                        System.out.println(isBigger);

                                    } else if (secretNumber < userGuess) {
                                        System.out.println(isSmaller);
                                    }
                                    System.out.println("Verbleibende Versuche: " + numberOfTries);

                                } else {
                                    System.out.println("Sie haben Ihre Versuche bereits aufgebraucht. Bis zum nächsten Mal.");
                                    isGameRoundOn = false;
                                }
                            }
                        }


                    }

                    System.out.println("\nRückkehr zum Hauptmenü...\n");
                    levelChoice = -1; // to reset the menu choice

                    break;

                case 2:

                    boolean isLevelTwoActive = true;
                    int numberOfAttempts = 9; //total number of tries

                    ArrayList<Integer> listOfGuessedNumbers = new ArrayList<>();

                    while (isLevelTwoActive && numberOfAttempts > 0) { //to reloop if the player decides to play again

                        boolean isGameRoundActive = true;
                        int numberToGuess; //the secret number
                        int playerGuess; //player Guess

                        System.out.println("***** ZahlenRatenSpiel Level 2 *****\n");
                        numberToGuess = rand.nextInt(101);

                        while (isGameRoundActive) {

                            System.out.println(numberToGuess); // just for debugging and checking

                            do {
                                System.out.println("\nErrate die versteckte Zahl. (1-100) ");
                                playerGuess = sc.nextInt();

                                if (playerGuess < 0 || playerGuess > 100) {
                                    System.out.println("\nUngültige Zahl. Zahl von 1 bis 100 eingeben:");
                                }

                            } while (playerGuess < 0 || playerGuess > 100);

                            if (listOfGuessedNumbers.contains(playerGuess)) {
                                System.out.println("Diese Zahl wurde bereits geraten. Versuch eine andere.");

                            } else { // run when the number is not on the list

                                listOfGuessedNumbers.add(playerGuess);
                                System.out.println(listOfGuessedNumbers);

                                if (numberToGuess == playerGuess) {
                                    listOfGuessedNumbers.clear();
                                    numberOfAttempts = 9;
                                    System.out.println("Richtig geraten!!!");

                                    char playStill;

                                    do {
                                        System.out.println("Möchten Sie noch einmal spielen? [y][n]");
                                        playStill = sc.next().charAt(0);

                                    } while (playStill != 'n' && playStill != 'y');


                                    if (playStill == 'n') {
                                        System.out.println("Vielen Dank fürs Spielen!");
                                        isLevelTwoActive = false;
                                        isGameRoundActive = false;
                                    } else if (playStill == 'y') {
                                        isGameRoundActive = false;
                                    }

                                } else {

                                    if (numberOfAttempts > 0) {

                                        int diff = Math.abs(playerGuess - numberToGuess); //ensure value is positive

                                        if (diff <= 3) {
                                            System.out.println("fast da: zwischen 1 und 3 daneben.");
                                        } else if (diff <= 10) {
                                            System.out.println("relative nahe: : zwischen 4 und 10 daneben");
                                        } else if (diff <= 20) {
                                            System.out.println("Nicht ganz so weit weg: zwischen 11 und 20 daneben");
                                        } else {
                                            System.out.println("weit weg, > daneben");
                                        }

                                        numberOfAttempts--;

                                        System.out.println("Your remaining chance to guess: " + numberOfAttempts);

                                    } else {
                                        System.out.println("You already exhausted Your chance, See you next time.");
                                        isGameRoundActive = false;
                                    }

                                }

                            }

                        } // end of isGameRoundActive

                    }

                    System.out.println("\nRückkehr zum Hauptmenü......\n");
                    levelChoice = -1; // to reset the menu choice
                    break;

                case 3:

                    System.out.println("\n***** ZahlenRatenSpiel Level 3 *****\n");

                    boolean playerTurn = rand.nextBoolean(); // to know who will start

                    int numberToGuess = rand.nextInt(101); // to generate the random number for guessing

                    boolean guessed = false;

                    int lowerBound = 0; // the lower Side to compute the median
                    int higherBound = 101; //the Upper Side +1 So that KI can still guess if random is 100.
                    int userGuess;
                    int kiGuess;

                    System.out.println(numberToGuess); // only for debugging and checking

                    ArrayList<Integer> listOfGuesses = new ArrayList<>();

                    while (!guessed) {

                        System.out.println(listOfGuesses);

                        //playerTurn
                        if (playerTurn) {
                            //to filter userInput within 0 to 100 range
                            do {
                                System.out.println("\nErrate die versteckte Zahl. (1-100) ");
                                userGuess = sc.nextInt();

                                if (userGuess < 0 || userGuess > 100) {
                                    System.out.println("\nUngültige Zahl. Zahl von 1 bis 100 eingeben:");
                                }

                            } while (userGuess < 0 || userGuess > 100);

                            if (listOfGuesses.contains(userGuess)) {

                                System.out.println("Diese Zahl wurde bereits geraten. Versuch eine andere.");

                            } else { // when number is not on the list

                                if (numberToGuess == userGuess) {
                                    System.out.println("Player 1: Your guess was correct");
                                    guessed = true;

                                } else if (numberToGuess > userGuess) {
                                    //to track userGuess Reply and KI will readjust the lowerbound
                                    if (userGuess > lowerBound) {
                                        lowerBound = userGuess + 1;
                                    }

                                    System.out.println(isBigger);

                                } else if (numberToGuess < userGuess) {
                                    //to track userGuess Reply and KI will readjust the higherBound
                                    if (userGuess < higherBound) {
                                        higherBound = userGuess - 1;
                                    }
                                    System.out.println(isSmaller);
                                }

                                listOfGuesses.add(userGuess);

                            }

                        } else { //KI turn

                            kiGuess = (lowerBound + higherBound) / 2;
                            listOfGuesses.add(kiGuess);
                            System.out.println("It's KI turn: " + kiGuess);


                            if (numberToGuess == kiGuess) {
                                System.out.println("KI, " + isCorrect);
                                listOfGuesses.add(kiGuess);
                                guessed = true;
                            } else if (numberToGuess > kiGuess) {
                                lowerBound = kiGuess + 1;
                                System.out.println(isBigger);
                            } else if (numberToGuess < kiGuess) {
                                higherBound = kiGuess - 1;
                                System.out.println(isSmaller);
                            }

                        }

                        playerTurn = !playerTurn; //to change turn
                    }

                    System.out.println("Vielen Dank fürs Spielen!");
                    levelChoice = -1;

                    break;

                default:

                    System.out.println("Vielen Dank fürs Spielen!");

            }

        } //end of menu

    }
}
