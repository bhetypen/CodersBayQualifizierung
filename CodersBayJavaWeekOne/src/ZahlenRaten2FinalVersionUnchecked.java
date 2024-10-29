import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ZahlenRaten2FinalVersionUnchecked {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int levelChoice = -1;

        while (levelChoice != 0) {

            System.out.println("\n***** ZahlenRatenSpiel *****");
            System.out.println("""
                    Wähle, welches Level du spielen möchtest. Gib eine Zahl zwischen 1 und 3 ein, 0 zum Beenden des Spiels.
                                        
                    0. Spiel Beenden
                    1. Level One
                    2. Level Two
                    3. Level Three
                    4. Level Four
                    """);

            while (levelChoice < 0 || levelChoice > 4) {
                System.out.println("Geben Sie Ihre Auswahl ein. z.B. (1 für Level 1): ");
                levelChoice = sc.nextInt();

                if (levelChoice < 0 || levelChoice > 4) {
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
                                        System.out.println("Großer! Die geheime Zahl ist größer.\n");

                                    } else if (secretNumber < userGuess) {
                                        System.out.println("Kleiner! Die geheime Zahl ist kleiner.\n");
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

                    //int numberToGuess = 3;
                    boolean guessed = false;

                    int lowerBound = 0; // the lower Side to compute the median
                    int higherBound = 101; //the Upper Side +1 So that KI can still guess if random is 100.
                    int userGuess = 0;
                    int kiGuess = 0;
                    int currentGuess = 0;

                    System.out.println(numberToGuess); // only for debugging and checking

                    ArrayList<Integer> listOfGuesses = new ArrayList<>();

                    while (!guessed) {

                        System.out.println(listOfGuesses);

                        //playerTurn  // need editing
                        if (playerTurn) {
                            //to filter userInput within 0 to 100 range
                            do {
                                System.out.println("\nErrate die versteckte Zahl. (1-100) ");
                                userGuess = sc.nextInt();

                                if (userGuess < 0 || userGuess > 100) {
                                    System.out.println("\nUngültige Zahl. Zahl von 1 bis 100 eingeben:");
                                }

                                if (listOfGuesses.contains(userGuess)) {

                                    System.out.println("Diese Zahl wurde bereits geraten. Versuch eine andere.");

                                }

                            } while ((userGuess < 0 || userGuess > 100) ||  listOfGuesses.contains(userGuess));


                            currentGuess = userGuess;
                            listOfGuesses.add(userGuess);

                        } else { //KI turn

                            kiGuess = (lowerBound + higherBound) / 2;
                            currentGuess = kiGuess;
                            listOfGuesses.add(kiGuess);
                            System.out.println("It's KI turn: " + kiGuess);

                        }

                        if (numberToGuess > currentGuess) {
                            System.out.println("Großer! Die geheime Zahl ist größer.\n");
                            if (currentGuess > lowerBound) {
                                lowerBound = currentGuess + 1;
                            }
                        } else if (numberToGuess < currentGuess) {
                            System.out.println("Kleiner! Die geheime Zahl ist kleiner.\n");

                            if (currentGuess < higherBound) {
                                higherBound = currentGuess - 1;
                            }

                        } else if (numberToGuess == currentGuess) {
                            guessed = true;
                            if (numberToGuess == kiGuess) {
                                System.out.println("KI guess was correct");
                            } else {
                                System.out.println("Your guess was correct");
                            }
                        }

                        playerTurn = !playerTurn; //to change turn
                    }

                    System.out.println("\nRückkehr zum Hauptmenü......\n");
                    levelChoice = -1; // to reset the menu choice
                    break;

                case 4:
                    System.out.println("\n***** ZahlenRatenSpiel Level 4 *****\n");

                    boolean currentPlayer = rand.nextBoolean();
                    int hiddenNumber = rand.nextInt(101);

                    //System.out.println(hiddenNumber);

                    ArrayList<Integer> list = new ArrayList<Integer>(); //holds the 0 - 100 int
                    ArrayList<Integer> guessList = new ArrayList<>(); //holds the list of guesses
                    for (int i = 0; i < 101; i++) {
                        list.add(i);
                    }

                    int player1 = 0; //user guess
                    int currentMove = 101;
                    int aiMove = 50; //computer guess
                    int minBound = 0; //the minBound if there is single range
                    int maxBound = 100; // the maxBound if there is single range

                    //the minBound and maxBound if there are two Range generated based on Feedback
                    int lowerBound1 = 0;
                    int higherBound1 = 0;
                    int lowerBound2 = 0;
                    int higherBound2 = 0;

                    boolean isGuessed = false;

                    while (!isGuessed) {

                        System.out.println("\nList of Guesses: " + guessList);

                        if (currentPlayer) {

                            do {
                                System.out.println("\nErrate die versteckte Zahl. (1-100) ");
                                player1 = sc.nextInt();

                                if (player1 < 0 || player1 > 100) {
                                    System.out.println("\nUngültige Zahl. Zahl von 1 bis 100 eingeben:");

                                } else if (guessList.contains(player1)) {

                                    System.out.println("Diese Zahl wurde bereits geraten. Versuch eine andere.");

                                }

                            } while ((player1 < 0 || player1 > 100) || guessList.contains(player1));

                            currentMove = player1;

                        } else {
                            currentMove = aiMove;
                            System.out.println("It's KI turn: " + aiMove);
                        }

                        list.remove((Integer) currentMove);
                        guessList.add(currentMove);
                        //System.out.println(list);

                        int diff = Math.abs(currentMove - hiddenNumber);

                        if (currentMove == hiddenNumber) {

                            isGuessed = true;

                            if (hiddenNumber == aiMove) {
                                System.out.println("KI guess was correct");
                            } else {
                                System.out.println("Your guess was correct");
                            }

                        } else if (diff <= 3) {
                            System.out.println("fast da: zwischen 1 und 3 daneben.");
                            minBound = Math.max(0, currentMove - 3);
                            maxBound = Math.min(100, currentMove + 3);

                            for (int i = list.size() - 1; i >= 0; i--) {
                                int num = list.get(i);
                                if (num < minBound || num > maxBound) {
                                    list.remove(i);
                                }
                            }

                        } else if (diff <= 10) {
                            System.out.println("relative nahe: : zwischen 4 und 10 daneben");
                            lowerBound1 = Math.max(0, currentMove - 10);
                            higherBound1 = currentMove - 4;

                            lowerBound2 = currentMove + 4;
                            higherBound2 = Math.min(100, currentMove + 10);


                            for (int i = list.size() - 1; i >= 0; i--) {
                                int num = list.get(i);

                                if ((num < lowerBound1 || num > higherBound1) && (num < lowerBound2 || num > higherBound2)) {
                                    list.remove(i);
                                }

                            }

                        } else if (diff <= 20) {

                            System.out.println("\nNicht ganz so weit weg: zwischen 11 und 20 daneben");

                            lowerBound1 = Math.max(0, currentMove - 20);
                            higherBound1 = (currentMove - 11);

                            lowerBound2 = currentMove + 11;
                            higherBound2 = Math.min(100, currentMove + 20);

                            for (int i = list.size() - 1; i >= 0; i--) {
                                int num = list.get(i);
                                if ((num < lowerBound1 || num > higherBound1) && (num < lowerBound2 || num > higherBound2)) {
                                    list.remove(i);
                                }
                            }

                        } else if (diff > 20) {
                            System.out.println("weit weg, > 20 daneben");

                            minBound = Math.max(0, currentMove - 21);
                            maxBound = Math.min(100, currentMove + 21);

                            for (int i = list.size() - 1; i >= 0; i--) {
                                int num = list.get(i);
                                if (num > minBound && num < maxBound) {
                                    list.remove(i);
                                }
                            }

                        }

                        //ai Guesses
                        if (currentMove != hiddenNumber ) {
                            if (list.size() <= 7) {
                                aiMove = list.getFirst();
                            } else if (list.size() <= 14) {
                                aiMove = list.get(list.size() / 2);
                            } else if (list.size() <= 20) {
                                aiMove = list.get(list.size() / 3);
                            } else {
                                aiMove = list.get(list.size() / 4);
                            }
                        }

                        currentPlayer = !currentPlayer; //to change turn
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
