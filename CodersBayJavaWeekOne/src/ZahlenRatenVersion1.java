import java.util.Random;
import java.util.Scanner;

public class ZahlenRatenVersion1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("\n***** ZahlenRatenSpiel *****");


        String isBigger = "Großer! Die geheime Zahl ist größer.\n";
        String isSmaller = "Kleiner! Die geheime Zahl ist kleiner.\n";
        String isCorrect = "Erraten! Deine Antwort ist korrekt.\n";


        int levelChoice  = 100;

        while (levelChoice != 0) {

            ystem.out.println """
                
                Wähle, welches Level du spielen möchtest. Gib eine Zahl zwischen 1 und 3 ein, 0 0 zum Beenden des Spiels.
            
                0. Spiel Beenden
                1. Level One
                2. Level Two
                3. Level Three
             
                """;

            S(menu);

            System.out.println("Geben Sie Ihre Auswahl ein. z.B. (1 für Level 1): ");
            levelChoice = sc.nextInt();

            switch (levelChoice) {
                case 1:

                     System.out.println("Du spielst Level 1\n");
                     int secretNumber = rand.nextInt(101);

                     System.out.println(secretNumber);

                     int guessAttempt = 9;
                     boolean levelOne = false;

                     while(guessAttempt != 0 && !levelOne) {
                         System.out.println("Errate die versteckte Zahl.");
                         int userGuess = sc.nextInt();
                         guessAttempt--;

                         if (secretNumber > userGuess) {
                             System.out.println(isBigger);

                         } else if (secretNumber < userGuess) {
                             System.out.println(isSmaller);
                         } else {
                             System.out.println(isCorrect);
                             System.out.println("Möchtest du noch spielen? [y/n]");
                             char answer = sc.next().charAt(0);



                                 if (answer == 'y') {
                                     guessAttempt = 9;
                                 } else if (answer == 'n'){
                                     levelOne = true;
                                 } else {
                                     System.out.println("Invalid input");
                                 }


                         }

                         System.out.println("Verbleibende Anzahl von Versuchen: " + guessAttempt + "\n");

                         if (guessAttempt == 0) {
                             System.out.println("Versuche: "+ guessAttempt + " - Du hast keine Versuche mehr.. Du hast das Spiel verloren. Bis zum nächsten Mal!");
                         }
                     }



                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
            }

        }

    }
}
