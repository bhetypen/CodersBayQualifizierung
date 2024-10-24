import java.util.Random;
import java.util.Scanner;

public class ArrayAssignment {
    private static final int SIZE = 10;
    private static final char[][] hiddenMap = new char[SIZE][SIZE];
    private static final int[][] map = new int[SIZE][SIZE];
    private static int mineCount = 0;
    private static int clearedFields = 0;

    public static void main(String[] args) {
        // Initialize map with random values between -3 and
        // 0
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = rand.nextInt(4) - 3;  // Random value between -3 and 0
                if (map[i][j] == 0) {
                    mineCount++;
                }
                hiddenMap[i][j] = ' ';  // Initialize hidden map with unknown spaces
            }
        }
        int nonMinedArea = (SIZE * SIZE) - mineCount;
        Scanner scanner = new Scanner(System.in);

        // Start game loop
        boolean gameOn = true;
        while (gameOn) {
            // Display the current map state
            System.out.print("      ");
            for (int i = 0; i < SIZE; i++) {
                System.out.print((char)('A' + i) + "   ");  // Using direct ASCII values
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {
                System.out.print(i + "   ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print("[" + hiddenMap[i][j] + "]");
                }
                System.out.println();
            }

            // Display the current progress
            System.out.printf("You have checked %d/%d (%.1f%%) of the non-mined area for mines.%n",
                    clearedFields, nonMinedArea, (clearedFields / (double) nonMinedArea) * 100);
            System.out.printf("There are still %d mines hidden.%n", mineCount);
            System.out.print("Where do you want to search for mines? (e.g., B4): ");
            String input = scanner.nextLine().toUpperCase();

            // Parse user input (e.g., "B4" -> row = 4, col = 1)
            if (input.length() != 2) {
                System.out.println("Invalid input. Please enter a letter and a number (e.g., A4).");
                continue;
            }
            char colLetter = input.charAt(0);
            int row = Character.getNumericValue(input.charAt(1));

            // Convert letter (A-J) directly into an index without using a string
            int col = -1;
            if (colLetter >= 'A' && colLetter <= 'J') {
                col = colLetter - 'A';  // This converts 'A'-'J' directly into 0-9
            }

            if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
                System.out.println("Invalid coordinates. Please choose within the grid.");
                continue;
            }

            // Process the user's move
            if (map[row][col] == 0) {
                hiddenMap[row][col] = '*';  // Mine hit
                System.out.print("      ");
                for (int i = 0; i < SIZE; i++) {
                    System.out.print((char)('A' + i) + "   ");  // Re-display map with columns
                }
                System.out.println();
                for (int i = 0; i < SIZE; i++) {
                    System.out.print(i + "   ");
                    for (int j = 0; j < SIZE; j++) {
                        System.out.print("[" + hiddenMap[i][j] + "]");
                    }
                    System.out.println();
                }
                System.out.println("That... was a mine. Unfortunately, you lost.");
                gameOn = false;
            } else {
                hiddenMap[row][col] = '-';  // Safe move
                clearedFields++;
                if (clearedFields == nonMinedArea) {
                    System.out.println("Congratulations! You've cleared all safe fields!");
                    gameOn = false;
                }
            }
        }
        scanner.close();
    }
}
