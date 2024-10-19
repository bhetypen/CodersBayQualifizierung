import java.util.Random;

public class CyberSecurityV1 {
    public static void main(String[] args) {
        Random r = new Random();

        char [] allowedCharacter = "ABCabc012!".toCharArray();

        int charLength = (allowedCharacter.length); //10 in length

        int passwordLength = 4;
        int passwordMatches = 0;
        int totalGuess = 0;
        int leastAmountofGuess = 0xffff;
        int highestAmountofGuess = 0;

        for (int i = 0; i < 10; i++) {

            StringBuilder password = new StringBuilder();

            //to generate password 1000 times
            for (int j = 0 ; j < passwordLength; j++) {
                int randomIndex = r.nextInt(allowedCharacter.length - 1);
                password.append(allowedCharacter[randomIndex]);
            }

            //double maxLength = Math.pow(allowedCharacter.length , passwordLength);


            boolean isFound = false;


            for (int x = 0 ; (x < 10000) && !isFound ; x++) {
                //StringBuilder guessPassword = new StringBuilder();


                int index1 = x % charLength;
                int index2 = (x / charLength) % charLength;
                int index3 = (int) ((x / (Math.pow(charLength, 2))) % charLength);
                int index4 = (int) ((x / (Math.pow(charLength, 3))) % charLength);

                String guessPassword = String.valueOf(allowedCharacter[index4]) +
                        allowedCharacter[index3] +
                        allowedCharacter[index2] +
                        allowedCharacter[index1];

               //System.out.println("guesspassword "+ guessPassword);
                //(System.out.println("password " +password.toString());



                if (guessPassword.contentEquals(password)) {

                    System.out.println("Took " + (x+1) + " guesses");
                    System.out.println("averageGues");

                    passwordMatches++;
                    totalGuess += x+1;

                    System.out.println(guessPassword);
                    System.out.println(password);
                    System.out.println("found");

                    isFound = true;

                    if((x+1) < leastAmountofGuess) {
                        leastAmountofGuess = x+1;
                    } else if ((x+1) > highestAmountofGuess) {
                        highestAmountofGuess = x+1;
                    }

                }





            }



        }


        System.out.println("The slowest guess is: " + highestAmountofGuess);
        System.out.println("the fastest guess is: " + leastAmountofGuess);
        System.out.println("average guesses: "+totalGuess/10);

    }
}

