public class GaudiMitArraysV2Version2 {
    public static void main(String[] args) {

        char [] myArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        System.out.println(myArray);

        System.out.println(myArray.length);

        int howMuch = -1;

        if (howMuch > 0) {

            for (int repetions = 0; repetions < howMuch; repetions++) {
                char lastCharacter = myArray[myArray.length - 1];

                for (int i = myArray.length - 1; i >= 1; i--) {
                    myArray[i] = myArray[i - 1];
                }
                myArray[0] = lastCharacter;
            }
        } else if (howMuch < 0){
            for (int repetions = 0; repetions < howMuch * -1; repetions++) {
                char firstCharacter = myArray[0];

                for (int i = 0; i <= myArray.length - 2; i++) {
                    myArray[i] = myArray[i +1];
                }
                myArray[myArray.length-1] = firstCharacter;
            }
        }

        System.out.println(myArray);
    }
}
