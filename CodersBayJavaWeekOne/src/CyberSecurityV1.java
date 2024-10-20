import java.util.Random;

public class CyberSecurityV1 {
    public static void main(String[] args) {
        Random r = new Random();

        char [] allowedCharacter = "ABCDabcd012!".toCharArray();

        int charLength = (allowedCharacter.length); //length of the allowedCharacter Array

        int passwordLength = 5; //random password fix length (for variable length i will add min and max)


        int passwordMatches = 0;
        int totalGuess = 0;
        int highestAmountofGuess = 0;

        int possibleGuesses = (int) Math.pow(charLength, passwordLength);
        int leastAmountofGuess = possibleGuesses + 1;



        for (int i = 0; i < 10; i++) { //for variable length password i = min value and i < max value
            int [] indexArr = new int[passwordLength]; // here is the i for current password length
            StringBuilder password = new StringBuilder();

            //to generate a fixed length character password
            for (int j = 0 ; j < passwordLength; j++) {
                int randomIndex = r.nextInt(allowedCharacter.length);
                password.append(allowedCharacter[randomIndex]);
            }

            System.out.println("\nPassword to Match: " +password);


            boolean isFound = false;


            for (int x = 0 ; (x < possibleGuesses) && !isFound ; x++) {

                //based N guessing the password raise to the power of passwordLength UnitsPlace starts at 10° = 1;
                for(int position = 0; position < passwordLength; position++)
                {
                    indexArr[position] = (int) ((x / (Math.pow(charLength, position))) % charLength);
                }
//                int index1 = x % charLength;
//                int index2 = (x / charLength) % charLength;
//                int index3 = (int) ((x / (Math.pow(charLength, 2))) % charLength);
//                int index4 = (int) ((x / (Math.pow(charLength, 3))) % charLength); //u.s.w ...

                StringBuilder guessPassword = new StringBuilder();
                for(int positions = passwordLength-1; positions >= 0 ; positions--)
                {
                    guessPassword.append(allowedCharacter[indexArr[positions]]);
                    // concatenated version guessPassword += String.valueOf(allowedCharacter[indexArr[positions]]);
                }
//                String guessPassword = String.valueOf(allowedCharacter[index4]) +
//                        allowedCharacter[index3] +
//                        allowedCharacter[index2] +
//                        allowedCharacter[index1];


                if (guessPassword.toString().contentEquals(password)) {

                    System.out.println("Generated Password: " + guessPassword + " = " + " Guess Password: " +guessPassword);
                    System.out.println("Password Match: " + guessPassword + " = " + password);
                    System.out.println("Took " + (x+1) + " guesses");

                    passwordMatches++;
                    totalGuess += x+1;

                    isFound = true;

                    if((x+1) < leastAmountofGuess) {
                        leastAmountofGuess = x+1;
                    } else if ((x+1) > highestAmountofGuess) {
                        highestAmountofGuess = x+1;
                    }

                }

            }

        }


        
        System.out.println("\nPossible amount of Guesses: " + possibleGuesses);
        System.out.println("Total guesses: " + totalGuess);
        System.out.println("Password Matches: " + passwordMatches);
        System.out.println("The slowest guess is: " + highestAmountofGuess);
        System.out.println("the fastest guess is: " + leastAmountofGuess);
        System.out.println("average guesses: "+totalGuess/passwordMatches);

    }
}

