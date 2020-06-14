package Chapter1.Chapter1_4;

import java.util.Arrays;

public class FarthestPair {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(farthest(new int[]{
                -909, -90, -39, 1, 5, 8, 44, 45, 6, 33, 90
        })));
        System.out.println(Arrays.toString(farthest(ReadInt.get())));
    }

    public static int[] farthest(int[] arr) {
        int num1 = 0;
        int num2 = 0;
        int diff = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            int localDiff = Math.abs(Math.abs(arr[i]) - Math.abs(arr[i + 1]));
            if (localDiff > diff) {
                num1 = arr[i];
                num2 = arr[i + 1];
                diff = localDiff;
            }
        }

        return new int[]{num1, num2};
    }
}
