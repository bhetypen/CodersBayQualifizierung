import java.util.ArrayList;
import java.util.Scanner;

public class RPGV1Version1 {
    public static void main(String[] args) {
        String[][] choices = {
                // {parentID, ID, choiceTxt, resultTxt, moveToID}
                {"-1", "0", "", "Du stehst in einer Bar."},
                {"0", "1", "Geh in die Wildnis", "Du bist in der Wildnis angekommen."},
                {"0", "2", "Trink ein Getränk", "Du lässt dir ein Getränk schmecken", "0"},
                {"1", "3", "Kämpf gegen das Monster", "Das Monster ist ein harter Gegner, aber du besiegst es.", "1"},
                {"1", "4", "Lauf vor dem Monster davon", "Du läufst wie ein Feigling zurück zur Bar.", "0"},
        };

        int currentChoice = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String moveToID = null;
            String resultText = "";

            // Display the result of the current choice
            for (String[] choice : choices) {
                if (Integer.parseInt(choice[1]) == currentChoice) {
                    resultText = choice[3];
                    System.out.println(resultText);

                    if (choice.length == 5) { //to prevent out of bound error
                        moveToID = (choice[4]);
                    }
                }
            }

            if (moveToID != null) {
                currentChoice = Integer.parseInt(moveToID);
            } else {
                System.out.println("----------------------------");
                ArrayList<Integer> viableChoices = new ArrayList<>();

                for (String[] choice : choices) {
                    if (Integer.parseInt(choice[0]) == currentChoice) {
                        System.out.println(choice[1] + ". " + choice[2]);
                        viableChoices.add(Integer.parseInt(choice[1]));
                    }
                }
                System.out.println("----------------------------");

                boolean validInput = false;
                while (!validInput) {
                    System.out.println("Was willst du als nächstes tun? ");
                    int playerChoice = scanner.nextInt();

                    if (viableChoices.contains(playerChoice)) { // checking the choice state using the arraylist viableChoices
                        currentChoice = playerChoice;
                        validInput = true;
                    } else {
                        System.out.println("Ungültige Auswahl. Bitte versuche es erneut.");
                    }
                }
            }
        }
    }
}


