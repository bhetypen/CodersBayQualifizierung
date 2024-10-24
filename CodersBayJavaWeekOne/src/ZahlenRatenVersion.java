import java.util.Random;
import java.util.Scanner;

public class ZahlenRatenVersion {
    public static void main(String[] args) {

        Random rand = new Random();
        int numberToGuess = rand.nextInt(101);

        System.out.println(numberToGuess);

        int[] originalArray = new int[101];

//        for (int i = 0; i < originalArray.length; i++) {
//            originalArray[i] = i;
//            System.out.print(originalArray[i] + "\s") ;
//        }

        int firstGuess = 0;
        System.out.println("first " + firstGuess);
        int userFeedback;

        do {

            String answer = """
                    1. „fast da“: zwischen 1 und 3 daneben.
                    2. „relativ nahe“: zwischen 4 und 10 daneben
                    3. „Nicht ganz so weit weg“: zwischen 11 und 20 daneben
                    4. „Weit weg“: > über 20 daneben
                    """;
            System.out.println("Enter  1 - 4 " + answer);
            Scanner scanner = new Scanner(System.in);

            userFeedback = scanner.nextInt();

            //int[] justArray = {0, 100};

            switch (userFeedback) {
                case 1:
                    firstGuess = firstGuess + 1;
                    //justArray[0] = firstGuess + 1;
                    //justArray[1] = firstGuess + 3;
                    break;
                case 2:
                    firstGuess = firstGuess + 4;
                    break;
                case 3:
                    firstGuess = firstGuess + 10;
                    break;
                case 4:
                    firstGuess = firstGuess + 21;
                    break;
                default:
            }

            System.out.println("moreGuesses"+firstGuess);

            firstGuess = Math.abs(firstGuess); // Ensure value is positive

            if (firstGuess <= 3) {
                System.out.println("fast da"); ;
            } else if (firstGuess <= 10) {
                System.out.println("relative nahe");
            } else if (firstGuess <= 20) {
                System.out.println("Nicht ganz so weit weg");
            } else {
                System.out.println("weit weg");
            }


        } while (firstGuess != numberToGuess);
    }
}
