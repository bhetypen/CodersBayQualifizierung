import java.util.Arrays;
import java.util.Random;

public class GaudiMitStatistikV2 {
    public static void main(String[] args) {

        Random r = new Random();
        int [] randomArr = new int[4];

        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = r.nextInt(5) - 3;
        }

        System.out.println("Unsorted Array: " + Arrays.toString(randomArr));


        for (int i = 0; i < randomArr.length ; i++) {
            for (int j = 0; j < randomArr.length - 1 ; j++) {
                if (randomArr[j] > randomArr[j + 1]) {
                    int temp = randomArr[j];
                    randomArr[j] = randomArr[j + 1];
                    randomArr[j + 1] = temp;
                }
            }
        }
        System.out.println("Sorted Array: " + Arrays.toString(randomArr));


    }
}
