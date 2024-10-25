import java.sql.Array;

public class GaudiMitArraysV2 {
    public static void main(String[] args) {

        char [] myCharArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        int length = myCharArray.length;

        int howMuch = -3; //test number of shift

        howMuch = howMuch % length; //for 100

        if (howMuch < 0) {
            howMuch = length + howMuch;
        }

        char [] bufferArray = new char[length];

        for (int i = 0; i < length; i++) {
            int newIndex = i + howMuch;
            //System.out.println("Index: " + newIndex);

            if (newIndex >= length) {
                newIndex = newIndex - length;
            }
            bufferArray[newIndex] = myCharArray[i];
        }
        System.out.println(bufferArray);
    }
}
