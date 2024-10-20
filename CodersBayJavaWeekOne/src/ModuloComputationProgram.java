import java.util.Arrays;
import java.util.Scanner;

public class ModuloComputationProgram {
    public static void main(String[] args) {

//        System.out.println("4 Char Password generated from ABCabc012! ");
//        System.out.println("4 Character Password: _ _ _ _");
//        System.out.println("4 Character Password: thousandPlace, hundredsPlace, tensPlace, onesPlace");

        char[] charArr = ("abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "!@#$%^&*()_+-=[]{},.<>?/").toCharArray();



        int passWordLength = 10;
        int charArrLength = charArr.length;
        int posssiblePasswordCombination = (int) Math.pow(charArrLength, passWordLength);

        System.out.println(passWordLength + " Char Password from " + Arrays.toString(charArr));
        System.out.println("charArrLength Length: " + charArrLength);

        System.out.println("Possible Combination: " + posssiblePasswordCombination);

        System.out.println("Enter a number from 1 to " + posssiblePasswordCombination + ": ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        //char [] charArr = {'A', 'B', 'C', 'a', 'b', 'c', '0', '1', '2', '!'};


        //convert to for Loop for dynamic charArrLength and passwordLength
//        int index3 = number % charArrLength; //loop through 0-9 then carry flag
//        int index2 = (number / charArrLength) % charArrLength; //loop through 0 - 9 then carry flag
//        int index1 = (number / (charArrLength * charArrLength)) % charArrLength;
//        int index0 = (number / (charArrLength * charArrLength * charArrLength)) % charArrLength;

        // using the carry flag i can avoid using 4 for nested loops.

        //simplified for loop
        //-------------------------------------------------
        int [] indices = new int [passWordLength];

        int divisor = 1; // this corresponds to the base 10° = 1 as the first divisor like in index3 = number/10° % 10

        for (int i = passWordLength -1 ; i >= 0 ; i--) {
            indices[i] = (number / divisor) % charArrLength;
            divisor *= charArrLength; // increment by raising the power 10 raise to 1 is 1 * charLength
        }

        StringBuilder passwordFromForLoop = new StringBuilder();

        for (int i = 0; i < indices.length; i++) {

            passwordFromForLoop.append(charArr[indices[i]]);
        }

        System.out.println("Password cracked from For Loop: " + passwordFromForLoop);
        //-----------------------------------------------------

        //System.out.println("Modulo result: " + index0 + index1 + index2 + index3);
        System.out.println("Generated Password at try No. " +  (number));
        //System.out.println("manual result: " + charArr[index0] + charArr[index1] + charArr[index2] + charArr[index3]);

    }
}
