import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeperV1Version1 {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int[][] map = new int[10][10]; //map with random -3 to 0
        int numberOfTries = 0; //it will count the user input
        int mineCount = 0; //for counting the mines


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = rand.nextInt(-3, 1);

                if (map[i][j] == 0) {
                    mineCount++;


                }
            }
        }

        int totalMine = mineCount ;

        boolean gameOn = true;
        char mine = '*'; //for field contains 0 / mines
        char nonMine = '-'; //for field other than zero / not a mine

        int row = 0;
        int col = 0;

        do {

            for (char label = 'A'; label <= 'J'; label++) {

                System.out.print("     " + label + " ");
            }
            System.out.println();

            for (int i = 0; i < map.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < map.length; j++) {

                    if (map[i][j] <= 0) {
                        System.out.print(" [   ] ");
                    } else if (map[i][j] == mine) {
                        System.out.print(" [ " + mine + " ] ");
                    } else if (map[i][j] == nonMine) {
                        System.out.print(" [ " + nonMine + " ] ");
                    }

                }
                System.out.println();

            }
            double minePercentage = ((double) numberOfTries / (100 - mineCount)) * 100;
            System.out.println("\nDu hast " + numberOfTries + "/" + (100 - mineCount) + " (" + String.format("%.1f", minePercentage) + "%) des nicht verminten Gebiets auf Minen gecheckt");
            System.out.println("Es bleiben noch " + mineCount + " Minen versteckt.");
            if (totalMine == mineCount) {

                System.out.println("\nWo willst du nach Minen suchen? z.B. A0,  B1 usw...: ");
                String playerMove = sc.nextLine();

                col = playerMove.charAt(0) - 'A';
                row = playerMove.charAt(1) - '0';
                System.out.println("User input: " + row + "/" + col);

                if (map[row][col] == 0) {
                    numberOfTries++;
                    map[row][col] = mine;
                    mineCount--;


                } else if (map[row][col] < 0) {
                    map[row][col] = nonMine;
                    numberOfTries++;
                }

            } else {
                System.out.println("Das... war eine Mine. Du hast leider verloren.");
                gameOn = false;
            }

        } while (gameOn);

    }
}
