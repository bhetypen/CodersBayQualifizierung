import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeperV1Version1 {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int [][] map = new int[10][10];
        int mineCount = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = rand.nextInt(4) - 3;

                if (map[i][j] == 0) {
                    mineCount++;
                }
            }
        }

        System.out.println("For Debugging Purposes");
        System.out.println("    ");
        for (char label = 'A'; label <= 'J'; label++) {
            System.out.print("   " + label +  "   " );
        }
        System.out.println();


        for (int i = 0; i < map.length; i++) {

            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    System.out.print("[  "  +  map[i][j]  + " ]" + " ");
                } else {
                    System.out.print("[ "  + map[i][j]  + " ]" + " ");
                }
            }
            System.out.println();
        }


        System.out.println("\nMine Count: " + mineCount+ "\n");

        char userMove = sc.next().charAt(0);

        System.out.println("    ");
        for (char label = 'A'; label <= 'J'; label++) {
            System.out.print("   " + label +  "   " );
        }
        System.out.println();

        for (int i = 0; i < map.length; i++) {

            for (int j = 0; j < map[i].length; j++) {
                System.out.println("[ ]");
            }
            System.out.println();
        }







    }
}
