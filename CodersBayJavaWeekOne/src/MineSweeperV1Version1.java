import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeperV1Version1 {
    //private static final char[][] hiddenMap = new char[10][10];
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int[][] map = new int[10][10]; //map with random -3 to 0
        char [][] hiddenMap = new char[10][10]; // to hide the numbers
        int numberOfTries = 0; //it will count the user input
        int mineCount = 0; //for counting the mines
        char hiddenChar = ' ';

        //String[][] charArr = new String[10][10]; // to check the characters using ascii value

        // populating the map with -3 to 0
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = rand.nextInt(4) - 3;

                if (map[i][j] == 0) {
                    mineCount++;
                }
                hiddenMap[i][j] = hiddenChar; // to hide the random numbers
                //charArr[i][j] = String.valueOf((char) (i + 65)) + String.valueOf((char) (j + 48));
            }
        }

        boolean gameOn = true;


        while (gameOn) {

            System.out.println("    ");
            for (char label = 'A'; label <= 'J'; label++) {
                System.out.print("     " + label + " ");
            }
            System.out.println();

            for (int i = 0; i < 10 ; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 10; j++) {
                    System.out.print(" [ " + hiddenMap[i][j] + " ] ");
                }
                System.out.println();
            }

            double minePercentage = ( (double) numberOfTries / (100 - mineCount)) * 100 ;
            System.out.println("\nDu hast " +numberOfTries + "/" + (100 - mineCount) + " (" + String.format("%.1f", minePercentage) + "%) des nicht verminten Gebiets auf Minen gecheckt");
            System.out.println("Es bleiben noch " + mineCount + " Minen versteckt.");

            System.out.println("\nWo willst du nach Minen suchen? z.B. A0,  B1 usw...: ");
            String playerMove = sc.nextLine();
            numberOfTries++;

            //checking the user Input ... alternative to charAt
            int row = playerMove.charAt(0) - 'A';
            int col = playerMove.charAt(1) - '0';
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    if (Objects.equals(charArr[i][j], playerMove)) {
//                        col = i;
//                        row = j;
//                    }
//                }
//            }

            System.out.println("User input: " + row + "/" + col);

            if (map[row][col] == 0) {
                System.out.println("Das... war eine Mine. Du hast leider verloren.");
                hiddenMap[row][col] = '*';
                gameOn = false;

                for (int i = 0; i < 10; i++) {
                    System.out.print(i + " ");
                    for (int j = 0; j < 10; j++) {

                        System.out.print(" [ " + hiddenMap[i][j] + " ] ");
                    }

                    System.out.println();
                }

            } else {
                hiddenMap[row][col] = '-';
                mineCount--;
            }

        }
    }
}
