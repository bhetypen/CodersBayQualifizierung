import java.util.Scanner;

public class BetrunkenV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wie alt bist du?");
        int age = scanner.nextInt();

        System.out.println("Hast du einen Führerschein dabei?");
        boolean license = scanner.nextBoolean();

        System.out.println("Wie viel Bier hast du getrunken?");
        int beer = scanner.nextInt();

        System.out.println("Wie viel Shots hast du getrunken?");
        int shots = scanner.nextInt();

        if ((age < 17) || !license || ( age < 19 && shots + beer > 0 ) || ( age >= 19 && shots + beer > 2) ) {
                System.out.println("Du darfst nicht Auto fahren");
        } else {
            System.out.println("Du darfst Auto fahren");
        }
    }
}
