import java.util.Scanner;

public class BetrunkenV1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wie viel Bier hast du getrunken? ");
        int beer = scanner.nextInt();

        System.out.println("Wie viel Shots hast du getrunken? ");
        int shots = scanner.nextInt();

        if (beer + shots == 0) {
            System.out.println("Gar nicht betrunken.");
        } else if (beer + shots <= 2) {
            System.out.println("Leich Betrunken");
        } else if (shots == 0 && beer + shots <= 6) {
            System.out.println("Betrunken");
        } else {
            System.out.println("Hoffnungslos betrunken");
        }
    }
}
